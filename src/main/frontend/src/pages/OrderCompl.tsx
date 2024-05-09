const OrderCompl = () => {
  const tempId = localStorage.getItem("tempId");
  const memberId = localStorage.getItem("memberId");
  const getPoint = async () => {
    const response = await fetch("");
  };
  return (
    <div className="h-screen bg-zinc-100 flex flex-col justify-center items-center">
      <img src="/img/Mcdonald_Logo.png" className="size-60" />
      <div>고객님의 주문번호는 {tempId}번입니다.</div>
      <div>감사합니다.</div>
      {memberId !== "-1" ? (
        <div>회원님의 적립금은 {}원입니다.</div>
      ) : (
        <div></div>
      )}
    </div>
  );
};

export default OrderCompl;
