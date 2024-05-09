type Category = {
  imgSrc: string;
  name: string;
  onClick?: () => void;
};

const Category = ({ imgSrc, name, onClick }: Category) => {
  return (
    <button
      className="w-full mt-1 flex flex-col justify-center items-center border border-gray-300 shadow-sm rounded"
      onClick={onClick}
    >
      <img src={imgSrc} className="w-15 h-12" />
      <span>{name}</span>
    </button>
  );
};

export default Category;
