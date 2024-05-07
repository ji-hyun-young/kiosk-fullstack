package com.project.hanaro.kiosk.members.service;

import com.project.hanaro.kiosk.members.domain.Member;
import com.project.hanaro.kiosk.members.dto.*;
import com.project.hanaro.kiosk.members.exception.MemberDuplException;
import com.project.hanaro.kiosk.members.exception.MemberInvalidException;
import com.project.hanaro.kiosk.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberJoinResponse memberJoin(MemberJoinRequest request) {
        if (memberRepository.findByLoginId(request.loginId()).isPresent()) {
            throw new MemberDuplException();
        }

        Member member = memberRepository.save(MemberJoinRequest.toEntity(request));
        return new MemberJoinResponse(member.getMemberId());
    }

    public MemberLoginResponse memberLogin(MemberLoginRequest request) {
        Member member = memberRepository.findByLoginId(request.loginId())
                .orElseThrow(MemberInvalidException::new);

        if (!member.getPassword().equals(request.password())) {
            throw new MemberInvalidException();
        }

        return new MemberLoginResponse(member.getMemberId());
    }

    public MemberGuestLoginResponse memberGuestLogin(MemberGuestLoginRequest request) {

        return new MemberGuestLoginResponse(-1L);
    }

}
