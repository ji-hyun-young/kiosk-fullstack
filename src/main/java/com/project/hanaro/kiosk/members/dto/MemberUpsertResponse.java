package com.project.hanaro.kiosk.members.dto;

import com.project.hanaro.kiosk.members.domain.Member;

public record MemberUpsertResponse(Long memberId) {

    public static MemberUpsertResponse fromEntity(Member member) {
        return new MemberUpsertResponse(member.getMemberId());
    }
}