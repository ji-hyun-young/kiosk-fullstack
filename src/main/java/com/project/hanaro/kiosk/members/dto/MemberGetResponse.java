package com.project.hanaro.kiosk.members.dto;

import com.project.hanaro.kiosk.members.domain.Member;
import com.project.hanaro.kiosk.members.vo.MemberRole;

import java.time.LocalDateTime;

public record MemberGetResponse(Long memberId, String loginId, String password, String nickname, Integer point, MemberRole role, LocalDateTime createdAt) {
    public static MemberGetResponse fromEntity(Member member) {
        return new MemberGetResponse(member.getMemberId(), member.getLoginId(), member.getPassword(), member.getNickname(), member.getPoint(), member.getRole(), member.getCreatedAt());
    }
}
