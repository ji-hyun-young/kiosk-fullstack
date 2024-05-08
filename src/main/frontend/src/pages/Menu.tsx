import { useNavigate } from "react-router-dom";
import { Button } from "../components/Button";
import Category from "../components/Category";

const Menu = () => {
  const navigate = useNavigate();

  return (
    <div className="h-[100vh]">
      <div>
        <img src="/img/ad1.jpg" />
      </div>
      {/* 상품 목록 */}
      <div className="h-3/5">
        <div className="flex justify-between items-center">
          <span className="font-bold mx-2">
            <span className="text-red-700">홍길동</span>님 환영합니다.
          </span>
          <button
            className="btn-green px-10 mt-1"
            onClick={() => navigate("/place")}
          >
            이전
          </button>
        </div>
        {/* 카테고리 */}
        <div className="w-1/4 mx-1 flex flex-col justify-center items-center">
          <img src="/img/Mcdonald_Logo.png" className="w-12 h-12" />
          <Category name="버거" imgSrc="/img/burger.png" />
          <Category name="세트" imgSrc="" />
          <Category name="해피밀" imgSrc="" />
          <Category name="커피" imgSrc="" />
          <Category name="디저트" imgSrc="" />
          <Category name="음료" imgSrc="" />
        </div>
        {/* 카테코리별 상품목록 */}
        <div className="w-3/4">
          <div></div>
        </div>
      </div>
      {/* 주문 선택 목록 */}
      <div className="h-1/6">
        <div className="bg-green-900 mt-2 flex justify-between">
          <span className="text-white ml-5">주문 내역</span>
          <div className="mr-8">
            <span className="text-white mx-2">총 가격: 0원</span>
            <span className="text-white">수량: </span>
          </div>
        </div>
      </div>
      <div>
        <Button variant="danger" className="px-20">
          주문 취소
        </Button>
        <Button variant="green" className="px-20">
          주문 완료
        </Button>
      </div>
    </div>
  );
};

export default Menu;
