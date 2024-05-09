package com.project.hanaro.kiosk.members.controller;

import com.project.hanaro.kiosk.members.dto.*;
import com.project.hanaro.kiosk.members.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        session.setAttribute("memberId", response.memberId());
        session.setAttribute("nickname", response.nickname());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guest")
    public ResponseEntity<MemberGuestLoginResponse> memberGuestLogin(@RequestBody MemberGuestLoginRequest request, HttpSession session) {
        MemberGuestLoginResponse response = memberService.memberGuestLogin(request);
        session.setAttribute("memberId", response.memberId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<Page<MemberGetResponse>> findMemberList (Pageable pageable) {
        Page<MemberGetResponse> response = memberService.findMembers(pageable);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/mem-del/{memberId}")
    public ResponseEntity<MemberUpsertResponse> deleteMember(@PathVariable Long memberId){
        MemberUpsertResponse response = memberService.deleteMember(memberId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberUpsertResponse> updateMember(@PathVariable Long memberId, @RequestBody MemberUpsertRequest memberUpsertRequest) {
        MemberUpsertResponse response = memberService.updateMember(memberId, memberUpsertRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/point/{memberId}")
    public ResponseEntity<MemberPointResponse> getPoint(@PathVariable Long memberId) {
        MemberPointResponse response = memberService.findPoint(memberId);
        return ResponseEntity.ok(response);
    }
}
