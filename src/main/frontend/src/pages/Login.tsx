import React, { useState } from "react";

const Login = () => {
  // 상태 관리를 위한 useState 훅 사용
  const [memberId, setMemberId] = useState("");
  const [password, setPassword] = useState("");

  // 로그인 함수
  const handleLogin = async () => {
    // 요청 바디
    const requestBody = {
      memberId: memberId,
      password: password,
    };

    try {
      const response = await fetch(
        "http://localhost:8080/api/v1/members/login",
        {
          method: "POST", // HTTP 메소드
          headers: {
            "Content-Type": "application/json", // 요청의 컨텐츠 타입
          },
          body: JSON.stringify(requestBody), // JSON 문자열로 변환
        }
      );

      if (!response.ok) {
        throw new Error("로그인 실패");
      }

      const responseData = await response.json(); // 응답 데이터를 JSON으로 파싱
      console.log(responseData); // 응답 로그 출력
      // 여기서 추가 작업을 수행할 수 있습니다. 예를 들어, 로그인 성공 시 홈 화면으로 리디렉션.
    } catch (error) {
      console.error(error); // 에러 로그 출력
    }
  };

  return (
    <div className="flex flex-col items-center  h-screen bg-green-900 p-10">
      <div className="flex items-center mb-10">
        <img src="/public/img/Mcdonald_Logo.png" className="w-24 h-24 mr-4" />
        <span className="text-2xl font-bold text-white">로그인 해주세요</span>
      </div>

      <div className="mb-4 w-full max-w-xs">
        <div className="flex items-center mb-2">
          <label htmlFor="memberId" className="mr-2 text-white">
            아이디
          </label>
          <input
            type="text"
            id="memberId"
            className="flex-1 ml-4 p-2 text-gray-700"
            placeholder="아이디 입력"
          />
        </div>
        <div className="flex items-center mb-2">
          <label htmlFor="password" className="mr-2 text-white">
            비밀번호
          </label>
          <input
            type="password"
            id="password"
            className="flex-1 p-2 text-gray-700"
            placeholder="비밀번호 입력"
          />
        </div>
        <button className="w-36 mt-7 mr-7 btn-login">취소</button>
        <button className="w-36 mt-7 btn-login" onClick={handleLogin}>
          로그인
        </button>
        <div className="flex flex-col items-center">
          <button className="w-36 mt-7 btn-login">비회원 입장</button>
          <p className="text-sm mt-28 text-white">
            *회원가입하시면 포인트 적립을 받을 수 있습니다.
          </p>
          <button className="w-36 mt-3 btn-login">회원가입</button>
        </div>
      </div>
    </div>
  );
};

export default Login;
