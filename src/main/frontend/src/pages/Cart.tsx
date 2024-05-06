import { Button } from "../components/Button";
import Order from "../components/Order";

const Cart = () => {
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
          <Order name="빅맥" price={5000} />
          <Order name="감자튀김" price={2000} />
          <div className="h-10 flex justify-center items-center border-b-2 border-gray-200 mx-4 my-2">
            <span>불고기 버거</span>
          </div>
          {/* 총 수량, 총 금액 */}
          {/* 버튼 */}
          <div className="flex justify-center">
            <Button variant="danger" className="px-10">
              추가 주문
            </Button>
            <Button variant="default" className="px-10">
              결제하기
            </Button>
          </div>
          {/* <button className="btn-danger">추가 주문</button> */}
          {/* <button className="btn-default">결제하기</button> */}
        </div>
      </div>
    </div>
  );
};

export default Cart;
