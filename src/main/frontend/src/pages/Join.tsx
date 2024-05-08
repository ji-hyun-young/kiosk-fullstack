import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Join = () => {
  // 상태 관리를 위한 useState 훅 사용
  const [loginId, setloginId] = useState("");
  const [password, setPassword] = useState("");
  const [nickname, setnickname] = useState("");
  const navigate = useNavigate();

  // 로그인 함수
  const handleJoin = async () => {
    // 요청 바디
    const requestBody = {
      loginId: loginId,
      password: password,
      nickname: nickname,
    };

    try {
      const response = await fetch(
        "http://localhost:8080/api/v1/members/join",
        {
          method: "POST", // HTTP 메소드
          headers: {
            "Content-Type": "application/json", // 요청의 컨텐츠 타입
          },
          body: JSON.stringify(requestBody), // JSON 문자열로 변환
        }
      );

      if (!response.ok) {
        throw new Error("회원가입 실패");
      }

      const responseData = await response.json(); // 응답 데이터를 JSON으로 파싱
      console.log(responseData); // 응답 로그 출입입

      alert("회원가입에 성공하였습니다.");
      navigate(-1);
    } catch (error) {
      console.error(error); // 에러 로그 출력
      alert("중복된 아이디 입니다.");
    }
  };

  return (
    <div className="flex flex-col items-center  h-screen bg-green-900 p-10">
      <div className="flex items-center mb-10">
        <img src="/img/Mcdonald_Logo.png" className="w-24 h-24 mr-4" />
        <span className="text-2xl font-bold text-white">회원가입 해주세요</span>
      </div>

      <div className="mb-4 w-full max-w-xs">
        <div className="flex items-center mb-2">
          <label htmlFor="loginId" className="mr-2 text-white">
            아이디
          </label>
          <input
            type="text"
            id="loginId"
            className="flex-1 ml-4 p-2 text-gray-700"
            placeholder="아이디 입력"
            value={loginId}
            onChange={(e) => setloginId(e.target.value)}
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
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="flex items-center mb-2">
          <label htmlFor="nickname" className="mr-2 text-white">
            닉네임
          </label>
          <input
            type="text"
            id="nickname"
            className="flex-1 ml-4 p-2 text-gray-700"
            placeholder="닉네임 입력"
            value={nickname}
            onChange={(e) => setnickname(e.target.value)}
          />
        </div>
        <button
          className="w-36 mt-7 mr-7 btn-cancel"
          onClick={() => navigate(-1)}
        >
          취소
        </button>
        <button className="w-36 mt-3 btn-join" onClick={handleJoin}>
          회원가입
        </button>
      </div>
    </div>
  );
};

export default Join;
