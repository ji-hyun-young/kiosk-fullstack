import { Route, Routes } from "react-router-dom";
import { OrderProvider } from "./contexts/order-context";
import "./App.css";
import Home from "./pages/Home";
import Place from "./pages/Place";
import Cart from "./pages/Cart";
import Menu from "./pages/Menu";
import Login from "./pages/Login";

import Join from "./pages/Join";
import Payment from "./pages/Payment";
import OrderCompl from "./pages/OrderCompl";

function App() {
  return (
    <OrderProvider>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/Join" element={<Join />} />
        <Route path="/place" element={<Place />} />
        <Route path="/order" element={<Cart />} />
        <Route path="/menu" element={<Menu />} />
        <Route path="/pay" element={<Payment />} />
        <Route path="/complete" element={<OrderCompl />} />
      </Routes>
    </OrderProvider>
  );
}

export default App;
