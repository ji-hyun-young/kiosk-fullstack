import { useNavigate } from "react-router-dom";
import { Button } from "../components/Button";
import Category from "../components/Category";
import { useEffect, useState } from "react";
import Pagination from "../components/Pagination";
import { useOrder } from "../contexts/order-context";
import Order from "../components/Order";

type Product = {
  imageUrl: string;
  name: string;
  option: string;
  price: number;
  productId: number;
  type: string;
};

const Menu = () => {
  const navigate = useNavigate();
  const { addItem, cart, totalCnt, totalPrice } = useOrder();

  const memberId = localStorage.getItem("memberId");
  const [productList, setProductList] = useState<Product[]>([]);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const productsPerPage = 9;

  const getProductList = async (category: string) => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/v1/products/${category}`,
        { method: "GET" }
      );
      // 카테고리별 get 요청 수정
      if (!response.ok) {
        throw new Error("상품 목록을 가져오는 데 실패했습니다.");
      }
      const data = await response.json();
      // 받은 데이터를 필요에 따라 처리
      console.log(data);
      setProductList(data.content);
    } catch (error) {
      console.error("API 요청 중 오류 발생:", error);
      // 에러 처리
    }
  };

  useEffect(() => {
    getProductList("suggest");
  }, []);

  // 현재 페이지의 상품 목록 계산
  const indexOfLastProduct: number = currentPage * productsPerPage;
  const indexOfFirstProduct: number = indexOfLastProduct - productsPerPage;
  const currentProducts: Product[] = productList.slice(
    indexOfFirstProduct,
    indexOfLastProduct
  );

  // 페이지 변경 핸들러
  const onPageChange = (pageNumber: number) => {
    setCurrentPage(pageNumber);
  };

  const handleCategoryChange = (category: string) => {
    // 해당 카테고리에 대한 상품 목록 가져오기
    getProductList(category);
    // 페이지를 첫 번째 페이지로 초기화
    setCurrentPage(1);
  };

  // 카테고리별 상품 목록을 클릭할 때 실행될 함수
  const addToCart = (product: Product) => {
    addItem({
      id: product.productId,
      name: product.name,
      price: product.price,
    });
    console.log(cart);
  };

  const uniqueCartItems = Array.from(new Set(cart.map((item) => item.id))).map(
    (id) => {
      return cart.find((item) => item.id === id);
    }
  );

  return (
    <div className="h-screen">
      <div>
        <img src="/img/ad1.jpg" />
      </div>
      {/* 상품 목록 */}
      <div className="h-3/5">
        <div className="flex justify-between items-center">
          {memberId !== "-1" ? (
            <span className="font-bold mx-2">
              {/* 회원별 이름 조회 기능 수정 */}
              <span className="text-red-700">홍길동</span>님 환영합니다.
            </span>
          ) : (
            <div></div>
          )}

          <button
            className="btn-green px-10 mt-1"
            onClick={() => navigate("/place")}
          >
            이전
          </button>
        </div>
        {/* 카테고리 */}
        <div className="flex">
          <div className="w-1/4 mx-1 flex flex-col justify-start items-center">
            <img src="/img/Mcdonald_Logo.png" className="w-12 h-12" />
            <Category
              name="추천메뉴"
              imgSrc="/img/burger_set.png"
              onClick={() => handleCategoryChange("suggest")}
            />
            <Category
              name="버거"
              imgSrc="/img/burger_set.png"
              onClick={() => handleCategoryChange("SINGLE")}
            />
            <Category
              name="세트"
              imgSrc="/img/burger_set.png"
              onClick={() => handleCategoryChange("SET")}
            />
            {/* <Category name="해피밀" imgSrc="/img/happymeal.png" /> */}
            {/* <Category name="커피" imgSrc="/img/coffee.png" /> */}
            {/* <Category name="디저트" imgSrc="/img/dessert.png" /> */}
            {/* <Category name="음료" imgSrc="/img/drink.png" /> */}
          </div>
          {/* 카테코리별 상품목록 */}
          <div className="w-3/4 grid grid-cols-3 gap-4">
            {currentProducts.map((product, index) => (
              <button
                key={index}
                className="size-32 bg-slate-100 rounded-lg"
                onClick={() => addToCart(product)}
              >
                <img
                  src={product.imageUrl}
                  alt={product.name}
                  className="w-full h-24 rounded-lg"
                />
                <div>{product.name}</div>
                <div>{product.price}</div>
                <div>{product.productId}</div>
              </button>
            ))}
          </div>
        </div>
      </div>
      {/* Pagination */}
      <div className="flex justify-center">
        <Pagination
          currentPage={currentPage}
          totalPages={Math.ceil(productList.length / productsPerPage)}
          onPageChange={onPageChange}
        />
      </div>
      {/* 주문 선택 목록 */}
      <div className="h-1/6">
        <div className="bg-green-900 mt-2 flex justify-between">
          <span className="text-white ml-5">주문 내역</span>
          <div className="mr-8">
            <span className="text-white mx-2">총 가격: {totalPrice}원</span>
            <span className="text-white">수량: {totalCnt}</span>
          </div>
        </div>
        {uniqueCartItems.map((item, index) => (
          <Order key={index} product={item!} />
        ))}
      </div>
      <div>
        <Button
          variant="danger"
          className="px-20"
          onClick={() => {
            navigate("/place");
          }}
        >
          주문 취소
        </Button>
        <Button
          variant="green"
          className="px-20"
          onClick={() => {
            navigate("/order");
          }}
        >
          주문 완료
        </Button>
      </div>
    </div>
  );
};

export default Menu;
