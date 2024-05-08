import {
  ReactNode,
  createContext,
  useCallback,
  useContext,
  useMemo,
  useReducer,
} from "react";
import { Product } from "../globalTypes";

type ProviderProps = {
  children: ReactNode;
};

type OrderContextProps = {
  totalCnt: number;
  totalPrice: number;
  saveItem: ({ id, name, price }: Product) => void;
  removeItem: (id: number) => void;
  cart: Product[];
};

type Action =
  | { type: "saveItem"; payload: Product }
  | { type: "removeItem"; payload: number };

const OrderContext = createContext<OrderContextProps>({
  totalCnt: 0,
  totalPrice: 0,
  saveItem: () => {},
  removeItem: () => {},
  cart: [],
});

const reducer = (cart: Product[], { type, payload }: Action) => {
  switch (type) {
    case "saveItem":
      return [...cart, payload];
    case "removeItem":
      return cart.filter((item: Product) => item.id !== payload);
    default:
      return cart;
  }
};

export const OrderProvider = ({ children }: ProviderProps) => {
  const [cart, dispatch] = useReducer(reducer, []);

  const saveItem = useCallback(
    ({ id, name, price }: Product) =>
      dispatch({ type: "saveItem", payload: { id, name, price } }),
    []
  );

  const removeItem = useCallback(
    (id: number) => dispatch({ type: "removeItem", payload: id }),
    []
  );

  const totalCnt = useMemo(() => cart.length, [cart]);

  const totalPrice = useMemo(
    () => cart.reduce((acc, cur) => acc + cur.price, 0),
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
