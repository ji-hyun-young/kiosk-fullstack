import { useOrder } from "../contexts/order-context";

const Payment = () => {
  const { totalCnt, totalPrice, cart } = useOrder();

  const memberId = localStorage.getItem("memberId");
  console.log(memberId);
  console.log(cart);

  const orderProductInsertRequests = cart.map((item) => ({
    product: {
      productId: item.id,
    },
    orderProductCount: item.quantity || 1, // 기본값으로 1 설정
    orderProductPrice: item.price,
  }));

  const requestOrder = async () => {
    const requestBody = {
      orderInsertRequest: {
        member: {
          memberId: memberId,
        },
        status: "PICK_UP",
      },
      orderProductInsertRequests: orderProductInsertRequests,
    };
    try {
      const response = await fetch("http://localhost:8080/api/v1/orders", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(requestBody),
      });
      if (!response.ok) {
        throw new Error("주문 등록 실패");
      }
      const data = await response.json();

      console.log(data);
    } catch (error) {
      console.error("API 요청 중 오류 발생:", error);
    }
  };

  // 3초 뒤에 requestOrder 함수 호출
  setTimeout(requestOrder, 3000);

  return (
    <div className="h-[100vh] bg-green-900">
      <div className="h-1/6 flex justify-center items-center">
        <span className="text-white text-lg font-bold tracking-wide">
          주문을 확인하세요.
        </span>
      </div>
      <div className="flex justify-center">
        <div className="h-15 w-5/6 bg-white rounded text-center flex justify-center items-center">
          <span className="font-bold mx-2">총 수량: {totalCnt}개</span>
          <span className="font-bold text-red-700 mx-2">
            총 가격: {totalPrice}원
          </span>
        </div>
      </div>
    </div>
  );
};

export default Payment;
