package com.project.hanaro.kiosk.members.service;

import com.project.hanaro.kiosk.members.domain.Member;
import com.project.hanaro.kiosk.members.dto.MemberJoinRequest;
import com.project.hanaro.kiosk.members.dto.MemberJoinResponse;
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
            throw new MemberInvalidException();
        }

        Member member = memberRepository.save(MemberJoinRequest.toEntity(request));
        return new MemberJoinResponse(member.getMemberId());
    }
}
