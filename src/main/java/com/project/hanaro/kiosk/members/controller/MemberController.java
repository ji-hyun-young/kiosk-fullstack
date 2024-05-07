package com.project.hanaro.kiosk.members.controller;

import com.project.hanaro.kiosk.members.dto.MemberJoinRequest;
import com.project.hanaro.kiosk.members.dto.MemberJoinResponse;
import com.project.hanaro.kiosk.members.dto.MemberLoginRequest;
import com.project.hanaro.kiosk.members.dto.MemberLoginResponse;
import com.project.hanaro.kiosk.members.repository.MemberRepository;
import com.project.hanaro.kiosk.members.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<MemberJoinResponse> memberJoin(@RequestBody MemberJoinRequest request) {
        MemberJoinResponse response = memberService.memberJoin(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> memberLogin(@RequestBody MemberLoginRequest request, HttpSession session) {
        MemberLoginResponse response = memberService.memberLogin(request);
        session.setAttribute("memberId", response.memberId()); // 세션에 memberId 저장
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return ResponseEntity.ok().build();
    }

}
