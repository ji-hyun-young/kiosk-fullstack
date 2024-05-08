import { ReactNode, createContext, useContext, useState } from "react";

type ProviderProps = {
  children: ReactNode;
};

type OrderContextProps = {
  totalCnt: number;
  totalPrice: number;
  updateTotalCnt: (count: number) => void;
  updateTotalPrice: (price: number) => void;
};

const OrderContext = createContext<OrderContextProps>({
  totalCnt: 1,
  totalPrice: 0,
  updateTotalCnt: () => {},
  updateTotalPrice: () => {},
});

export const OrderProvider = ({ children }: ProviderProps) => {
  const [totalCnt, setTotalCount] = useState(1);
  const [totalPrice, setTotalPrice] = useState(0);

  const updateTotalCnt = (count: number) => {
    setTotalCount(count);
  };

  const updateTotalPrice = (price: number) => {
    setTotalPrice(price);
  };

  return (
    <OrderContext.Provider
      value={{ totalCnt, totalPrice, updateTotalCnt, updateTotalPrice }}
    >
      {children}
    </OrderContext.Provider>
  );
};

// eslint-disable-next-line react-refresh/only-export-components
export const useOrder = () => useContext(OrderContext);
