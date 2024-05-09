import {
  ReactNode,
  createContext,
  useCallback,
  useContext,
  useMemo,
  useReducer,
} from "react";
import { Item } from "../globalTypes";

type ProviderProps = {
  children: ReactNode;
};

type OrderContextProps = {
  totalCnt: number;
  totalPrice: number;
  addItem: ({ id, name, price }: Item) => void;
  removeItem: (id: number) => void;
  increaseQuantity: (id: number) => void;
  decreaseQuantity: (id: number) => void;
  cart: Item[];
};

type Action =
  | { type: "addItem"; payload: Item }
  | { type: "removeItem"; payload: number }
  | { type: "increaseQuantity"; payload: number }
  | { type: "decreaseQuantity"; payload: number };

const OrderContext = createContext<OrderContextProps>({
  totalCnt: 0,
  totalPrice: 0,
  addItem: () => {},
  removeItem: () => {},
  increaseQuantity: () => {},
  decreaseQuantity: () => {},
  cart: [],
});

const reducer = (cart: Item[], { type, payload }: Action) => {
  switch (type) {
    case "addItem":
      const existingItemIndex = cart.findIndex(
        (item) => item.id === payload.id
      );
      if (existingItemIndex !== -1) {
        const updatedCart = [...cart];
        updatedCart[existingItemIndex].quantity!++;
        return updatedCart;
      } else {
        return [...cart, { ...payload, quantity: 1 }];
      }
    case "increaseQuantity":
      return cart.map((item) =>
        item.id === payload ? { ...item, quantity: item.quantity! + 1 } : item
      );
    case "decreaseQuantity":
      return cart.map((item) =>
        item.id === payload ? { ...item, quantity: item.quantity! - 1 } : item
      );
    case "removeItem":
      return cart.filter((item: Item) => item.id !== payload);
    default:
      return cart;
  }
};

export const OrderProvider = ({ children }: ProviderProps) => {
  const [cart, dispatch] = useReducer(reducer, []);

  const addItem = useCallback(
    ({ id, name, price }: Item) =>
      dispatch({ type: "addItem", payload: { id, name, price } }),
    []
  );

  const increaseQuantity = useCallback(
    (id: number) => dispatch({ type: "increaseQuantity", payload: id }),
    []
  );

  const decreaseQuantity = useCallback(
    (id: number) => dispatch({ type: "decreaseQuantity", payload: id }),
    []
  );

  const removeItem = useCallback(
    (id: number) => dispatch({ type: "removeItem", payload: id }),
    []
  );

  const totalCnt = useMemo(
    () => cart.reduce((acc, cur) => acc + cur.quantity!, 0),
    [cart]
  );

  const totalPrice = useMemo(
    () => cart.reduce((acc, cur) => acc + cur.price * cur.quantity!, 0),
    [cart]
  );

  return (
    <OrderContext.Provider
      value={{
        totalCnt,
        totalPrice,
        addItem,
        removeItem,
        increaseQuantity,
        decreaseQuantity,
        cart,
      }}
    >
      {children}
    </OrderContext.Provider>
  );
};

// eslint-disable-next-line react-refresh/only-export-components
export const useOrder = () => useContext(OrderContext);
