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
  saveItem: ({ id, name, price }: Item) => void;
  removeItem: (id: number) => void;
  cart: Item[];
};

type Action =
  | { type: "saveItem"; payload: Item }
  | { type: "removeItem"; payload: number };

const OrderContext = createContext<OrderContextProps>({
  totalCnt: 0,
  totalPrice: 0,
  saveItem: () => {},
  removeItem: () => {},
  cart: [],
});

const reducer = (cart: Item[], { type, payload }: Action) => {
  switch (type) {
    case "saveItem":
      const existingItemIndex = cart.findIndex(
        (item) => item.id === payload.id
      );
      if (existingItemIndex !== -1) {
        // If item already exists in cart, update its quantity
        const updatedCart = [...cart];
        updatedCart[existingItemIndex].quantity!++;
        return updatedCart;
      } else {
        // If item is not in cart, add it with quantity 1
        return [...cart, { ...payload, quantity: 1 }];
      }
    case "removeItem":
      return cart.filter((item: Item) => item.id !== payload);
    default:
      return cart;
  }
};

export const OrderProvider = ({ children }: ProviderProps) => {
  const [cart, dispatch] = useReducer(reducer, []);

  const saveItem = useCallback(
    ({ id, name, price }: Item) =>
      dispatch({ type: "saveItem", payload: { id, name, price } }),
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
        saveItem,
        removeItem,
        cart,
      }}
    >
      {children}
    </OrderContext.Provider>
  );
};

// eslint-disable-next-line react-refresh/only-export-components
export const useOrder = () => useContext(OrderContext);
