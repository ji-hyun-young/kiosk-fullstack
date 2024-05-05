const Place = () => {
  return (
    <div className="w-128 h-128 bg-green-900">
      <div className="h-1/4 flex justify-evenly items-center">
        <img src="/img/Mcdonald_Logo.png" className="w-24 h-24" />
        <span className="text-white font-bold">
          식사하실 장소를 선택해주세요
        </span>
      </div>
      <div className="h-3/4 flex justify-around">
        <div className="bg-white w-1/3 h-1/2 rounded flex flex-col justify-center items-center">
          <img src="/img/1955 burger meal.png" className="w-24 h-24" />
          <span className="mt-10 text-red-600 font-bold">매장에서 식사</span>
        </div>
        <div className="bg-white w-1/3 h-1/2 rounded flex flex-col justify-center items-center">
          <img src="/img/to_go.jpg" className="w-24 h-24" />
          <span className="mt-10 text-red-600 font-bold">테이크 아웃</span>
        </div>
      </div>
    </div>
  );
};

export default Place;
