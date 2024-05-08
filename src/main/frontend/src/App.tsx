import { Route, Routes } from "react-router-dom";
import "./App.css";
import Home from "./pages/Home";
import Place from "./pages/Place";
import Cart from "./pages/Cart";

import Login from "./pages/Login";
import Join from "./pages/Join";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/Join" element={<Join />} />
      <Route path="/place" element={<Place />} />
      <Route path="/order" element={<Cart />} />
    </Routes>
  );
}

export default App;
