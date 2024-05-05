import { Route, Routes } from "react-router-dom";
import "./App.css";
import Home from "./pages/Home";
import Place from "./pages/Place";

function App() {
  return (
    <>
      <div className="container flex justify-center items-center">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/place" element={<Place />} />
        </Routes>
      </div>
    </>
  );
}

export default App;
