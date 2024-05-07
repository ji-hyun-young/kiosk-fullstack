package com.project.hanaro.kiosk.members.dto;

import com.project.hanaro.kiosk.members.domain.Member;
import com.project.hanaro.kiosk.members.vo.MemberRole;

public record MemberJoinRequest(String loginId, String password, String nickname) {
    public static Member toEntity(MemberJoinRequest request) {
        return Member.builder()
                .loginId(request.loginId)
                .password(request.password)
                .nickname(request.nickname)
                .point(0)
                .role(MemberRole.USER)
                .build();
    }
}
