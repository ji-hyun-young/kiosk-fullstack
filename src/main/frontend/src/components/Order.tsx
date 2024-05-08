import { useState } from "react";
import { Product } from "../globalTypes";
import { useOrder } from "../contexts/order-context";

type Props = {
  product: Product;
};

const Order = ({ product }: Props) => {
  const { updateTotalCnt } = useOrder();
  const [count, setCount] = useState(1);

  // useEffect(() => {
  //   updateTotalCnt(count);
  // }, [count, updateTotalCnt, totalCnt]);

  const plusCount = () => {
    setCount((prev) => {
      const newCnt = prev + 1;
      updateTotalCnt(newCnt);
      return newCnt;
    });
  };

  const minusCount = () => {
    setCount((prev) => prev - 1);
  };

  const handleCount = (e: React.MouseEvent<HTMLButtonElement>) => {
    const name = e.currentTarget.name;

    if (name === "plusBtn") {
      plusCount();
    }

    if (name === "minusBtn") {
      if (count === 1) {
        return;
      }
      minusCount();
    }
  };
  return (
    <div className="h-10 flex justify-center items-center border-b-2 border-gray-200 mx-4 my-2">
      <span className="mx-1">{product.name}</span> -{" "}
      <span className="mx-1">{product.price}원</span>
      <button
        className="border size-5 bg-gray-50 rounded text-center flex justify-center items-center mx-1"
        name="minusBtn"
        onClick={handleCount}
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
      <div>{count}개</div>
      <button
        className="border size-5 bg-gray-50 rounded text-center flex justify-center items-center mx-1"
        name="plusBtn"
        onClick={handleCount}
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
      <button className="btn-delete">삭제</button>
    </div>
  );
};

export default Order;
