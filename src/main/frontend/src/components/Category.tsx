type Category = {
  imgSrc: string;
  name: string;
};

const Category = ({ imgSrc, name }: Category) => {
  return (
    <div className="w-full mt-1 flex flex-col justify-center items-center border border-gray-300 shadow-sm rounded">
      <img src={imgSrc} className="w-15 h-12" />
      <span>{name}</span>
    </div>
  );
};

export default Category;
