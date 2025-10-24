package com.synapse.synapse.domain.kiosk.order.entity;

import com.synapse.synapse.domain.admin.dashboard.entity.StoreInfo;
import com.synapse.synapse.domain.kiosk.order.model.OrderType;
import com.synapse.synapse.domain.kiosk.order.model.Status;
import com.synapse.synapse.domain.user.entity.CreateQRUser;
import com.synapse.synapse.domain.user.entity.User;
import com.synapse.synapse.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //매장 정보 (하나의 가게는 여러 주문을 가질 수 있음)
    @ManyToOne(fetch =  FetchType.LAZY, optional = false)
    @JoinColumn(name ="storeId", nullable = false)
    private StoreInfo storeInfo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    //한사용자 - 동시주문 가능
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_user_id")
    private CreateQRUser qrUser;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Integer queueNumber;

}
