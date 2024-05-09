import { Item } from "../globalTypes";
import { useOrder } from "../contexts/order-context";

type Props = {
  product: Item;
};

const Order = ({ product }: Props) => {
  const { removeItem, cart, increaseQuantity, decreaseQuantity } = useOrder();

  const itemInCart = cart.find((item) => item.id === product.id);
  const quantity = itemInCart ? itemInCart.quantity : 1;

  const plusCount = (id: number) => {
    increaseQuantity(id);
  };

  const minusCount = (id: number) => {
    if (quantity === 1) {
      return;
    }

    decreaseQuantity(id);
  };

  const handleDelete = () => {
    removeItem(product.id);
  };

  return (
    <div className="h-10 flex justify-center items-center border-b-2 border-gray-200 mx-4 my-2">
      <span className="mx-1">{product.name}</span> -{" "}
      <span className="mx-1">{product.price}원</span>
      <button
        className="border size-5 bg-gray-50 rounded text-center flex justify-center items-center mx-1"
        name="minusBtn"
        onClick={() => minusCount(product.id)}
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 16 16"
          fill="currentColor"
          className="w-4 h-4"
        >
          <path d="M3.75 7.25a.75.75 0 0 0 0 1.5h8.5a.75.75 0 0 0 0-1.5h-8.5Z" />
        </svg>
      </button>
      <div>{product.quantity}개</div>
      <button
        className="border size-5 bg-gray-50 rounded text-center flex justify-center items-center mx-1"
        name="plusBtn"
        onClick={() => plusCount(product.id)}
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 16 16"
          fill="currentColor"
          className="w-4 h-4"
        >
          <path d="M8.75 3.75a.75.75 0 0 0-1.5 0v3.5h-3.5a.75.75 0 0 0 0 1.5h3.5v3.5a.75.75 0 0 0 1.5 0v-3.5h3.5a.75.75 0 0 0 0-1.5h-3.5v-3.5Z" />
        </svg>
      </button>
      <button className="btn-delete" onClick={handleDelete}>
        삭제
      </button>
    </div>
  );
};

export default Order;
