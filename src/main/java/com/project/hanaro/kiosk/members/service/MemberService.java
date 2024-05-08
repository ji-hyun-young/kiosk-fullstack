package com.project.hanaro.kiosk.members.service;

import com.project.hanaro.kiosk.members.domain.Member;
import com.project.hanaro.kiosk.members.dto.*;
import com.project.hanaro.kiosk.members.exception.MemberDuplException;
import com.project.hanaro.kiosk.members.exception.MemberInvalidException;
import com.project.hanaro.kiosk.members.exception.MemberNotFoundException;
import com.project.hanaro.kiosk.members.repository.MemberRepository;
import com.project.hanaro.kiosk.products.domain.Product;
import com.project.hanaro.kiosk.products.dto.ProductGetResponse;
import com.project.hanaro.kiosk.products.dto.ProductUpsertRequest;
import com.project.hanaro.kiosk.products.dto.ProductUpsertResponse;
import com.project.hanaro.kiosk.products.exception.ProductNotFoundException;
import com.project.hanaro.kiosk.products.vo.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public Page<MemberGetResponse> findMembers(Pageable pageable){
        Page<Member> members = memberRepository.findAll(pageable);
        List<MemberGetResponse> memberList = members.stream()
                .map(MemberGetResponse::fromEntity).collect(Collectors.toList());

        return new PageImpl<>(memberList, pageable, members.getTotalElements());
    }

    public MemberGetResponse findMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
        return MemberGetResponse.fromEntity(member);
    }

    @Transactional
    public MemberUpsertResponse deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        member.setDeleteYn(true);
        return MemberUpsertResponse.fromEntity(member);
    }

    @Transactional
    public MemberUpsertResponse updateMember(Long memberId, MemberUpsertRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        Member savedMember = request.updateEntity(member);
        return MemberUpsertResponse.fromEntity(savedMember);
    }

}
