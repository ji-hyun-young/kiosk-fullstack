package com.project.hanaro.kiosk.members.domain;


import com.project.hanaro.kiosk.common.domain.BaseEntity;
import com.project.hanaro.kiosk.members.vo.MemberRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long memberId;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "point")
    private Integer point;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private MemberRole role;
}