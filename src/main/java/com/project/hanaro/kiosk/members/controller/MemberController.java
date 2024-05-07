package com.project.hanaro.kiosk.members.controller;

import com.project.hanaro.kiosk.members.dto.MemberJoinRequest;
import com.project.hanaro.kiosk.members.dto.MemberJoinResponse;
import com.project.hanaro.kiosk.members.repository.MemberRepository;
import com.project.hanaro.kiosk.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class MemberController {

    private final MemberService memberService;
    @PostMapping("members/join")
    public ResponseEntity<MemberJoinResponse> memberJoin(@RequestBody MemberJoinRequest request) {
        MemberJoinResponse response = memberService.memberJoin(request);
        return ResponseEntity.ok(response);
    }
}
