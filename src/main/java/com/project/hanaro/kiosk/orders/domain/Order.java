package com.project.hanaro.kiosk.orders.domain;

import com.project.hanaro.kiosk.common.domain.BaseEntity;
import com.project.hanaro.kiosk.members.domain.Member;
import com.project.hanaro.kiosk.orders.vo.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "orders")
@Getter
@Setter
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
    private Integer tempId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name="delete_yn")
    private Boolean deleteYn;

    @Builder
    public Order(Member member, String code, Integer tempId, OrderStatus status) {
        this.member = member;
        this.code = code;
        this.tempId = tempId;
        this.status = status;
    }

    public void setDeleteYn(boolean deleteYn) {
        this.deleteYn = deleteYn;
    }
}
