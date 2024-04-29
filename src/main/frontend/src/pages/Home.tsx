const Home = () => {
  return (
    <>
      <div className="w-128 h-128 flex flex-col">
        <div className="bg-orange-700 h-4/5"></div>
        <div className="flex justify-around items-center bg-green-900 h-1/5">
          <img src="/public/img/Mcdonald_Logo.png" className="w-24 h-24" />
          <span className="text-white font-bold">주문하시려면 터치하세요.</span>
        </div>
      </div>
    </>
  );
};

export default Home;
