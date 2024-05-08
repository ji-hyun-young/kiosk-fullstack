package com.project.hanaro.kiosk.members.dto;

import com.project.hanaro.kiosk.members.domain.Member;
import com.project.hanaro.kiosk.members.vo.MemberRole;
import org.openapitools.jackson.nullable.JsonNullable;

// 회원 정보 업데이트 요청 DTO
public record MemberUpsertRequest(
        JsonNullable<String> loginId,
        JsonNullable<String> password,
        JsonNullable<String> nickname,
        JsonNullable<Integer> point,
        JsonNullable<String> role) {

    // DTO를 Member 엔티티로 변환
    public static Member toEntity(MemberUpsertRequest request) {

        return Member.builder()
                .loginId(request.loginId.get())
                .password(request.password.get())
                .nickname(request.nickname.get())
                .role(MemberRole.valueOf(request.role.get())) // role 처리는 이미 적절히 되어 있음
                .point(request.point.get()) // point 값을 처리. 기본값이 필요하다면 변경할 수 있음
                .build();
    }

    // 기존 Member 엔티티 업데이트
    public Member updateEntity(Member member) {
        loginId.ifPresent(member::setLoginId);
        password.ifPresent(member::setPassword);
        nickname.ifPresent(member::setNickname);
        point.ifPresent(member::setPoint);
        role.ifPresent(r -> member.setRole(MemberRole.valueOf(r)));
        return member;
    }
}
