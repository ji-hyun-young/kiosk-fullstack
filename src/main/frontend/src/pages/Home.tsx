import { useNavigate } from "react-router-dom";

const Home = () => {
  const navigate = useNavigate();
  return (
    <section className="h-[100vh]" onClick={() => navigate("/login")}>
      <div className="bg-orange-700 h-[80vh]"></div>
      <div className="flex justify-around items-center bg-green-900 h-[20vh]">
        <img src="/public/img/Mcdonald_Logo.png" className="w-24 h-24" />
        <span className="text-white font-bold">주문하시려면 터치하세요.</span>
      </div>
    </section>
  );
};

export default Home;
