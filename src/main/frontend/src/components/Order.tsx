const Order = ({ name, price }) => {
  return (
    <div className="h-10 flex justify-center items-center border-b-2 border-gray-200 mx-4 my-2">
      <span>{name}</span> - <span>{price}</span>
    </div>
  );
};

export default Order;
