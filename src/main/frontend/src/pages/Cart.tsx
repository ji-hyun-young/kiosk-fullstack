import { Button } from "../components/Button";
import Order from "../components/Order";

const Cart = () => {
  const product = {
    id: 1,
    name: "불고기 버거",
    price: 5000,
  };
  return (
    <div className="w-128 h-128 bg-green-900">
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
          {/* 버튼 */}
          <div className="flex justify-center">
            <Button variant="danger" className="px-10">
              추가 주문
            </Button>
            <Button variant="green" className="px-10">
              결제하기
            </Button>
          </div>
          {/* <button className="btn-danger">추가 주문</button> */}
          {/* <button className="btn-green">결제하기</button> */}
        </div>
      </div>
    </div>
  );
};

export default Cart;
