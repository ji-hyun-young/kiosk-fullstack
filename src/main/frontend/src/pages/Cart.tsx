import Order from "../components/Order";
import { useOrder } from "../contexts/order-context";

const Cart = () => {
  const product = {
    id: 1,
    name: "불고기 버거",
    price: 5000,
  };
  const { totalCnt, totalPrice } = useOrder();

  return (
    <div className="h-[100vh] bg-green-900">
      <div className="h-1/5 flex justify-center items-center">
        <span className="text-white text-lg font-bold tracking-wide">
          주문을 확인하세요.
        </span>
      </div>
      <div className="flex justify-center">
        <div className="bg-white rounded w-5/6">
          {/* 컴포넌트 만들기 */}
          <Order product={product} />
          <Order product={product} />

          {/* 총 수량, 총 금액 */}
          <div className="h-10 flex justify-end items-end border-b-2 border-gray-200 mx-4 my-2">
            <div className="font-bold mx-1">총 수량 : {totalCnt}개</div>
            <div className="font-bold mx-1 text-red-700">
              총 가격 : {totalPrice}원
            </div>
          </div>
          {/* 버튼 */}
          <div className="flex justify-center">
            <button className="btn-danger px-10">추가 주문</button>
            <button className="btn-green px-10">결제하기</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Cart;
