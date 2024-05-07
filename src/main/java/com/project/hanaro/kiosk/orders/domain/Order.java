package com.project.hanaro.kiosk.orders.domain;

import com.project.hanaro.kiosk.common.domain.BaseEntity;
import com.project.hanaro.kiosk.members.domain.Member;
import com.project.hanaro.kiosk.orders.vo.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "code")
    private String code;

    @Column(name = "temp_id")
    private Long tempId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    public Order(Member member, String code, Long tempId, OrderStatus status) {
        this.member = member;
        this.code = code;
        this.tempId = tempId;
        this.status = status;
    }
}
