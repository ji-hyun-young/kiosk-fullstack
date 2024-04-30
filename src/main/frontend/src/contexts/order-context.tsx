import { ReactNode, createContext, useContext } from "react";

type ProviderProps = {
  children: ReactNode;
};

const OrderContext = createContext("");

export const OrderProvider = ({ children }: ProviderProps) => {
  return <OrderContext.Provider value={""}>{children}</OrderContext.Provider>;
};

// eslint-disable-next-line react-refresh/only-export-components
export const useOrder = () => useContext(OrderContext);
