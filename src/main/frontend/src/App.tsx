import { Route, Routes } from "react-router-dom";
import "./App.css";
import Home from "./pages/Home";
import Place from "./pages/Place";
import Cart from "./pages/Cart";

import Login from "./pages/Login";
import { OrderProvider } from "./contexts/order-context";
import Menu from "./pages/Menu";

function App() {
  return (
    <OrderProvider>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/place" element={<Place />} />
        <Route path="/order" element={<Cart />} />
        <Route path="/menu" element={<Menu />} />
      </Routes>
    </OrderProvider>
  );
}

export default App;
