package com.synapse.synapse.domain.admin.dashboard.entity;

import com.synapse.synapse.domain.admin.Admin;
import com.synapse.synapse.domain.kiosk.order.entity.Order;
import com.synapse.synapse.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column(nullable = false)
    private Integer salesToday; //오늘 매출 금액

    @Column(nullable = false)
    private Integer salesYesterday; //어제 매출 금액

    @Column(nullable = false)
    private Float salesGrowthRate; //증가율

    //월별 매출
    @Builder.Default
    @OneToMany(mappedBy = "storeInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MonthlySales> monthlySales = new ArrayList<>();

    //주문 (매장은 여러 주문을 가질수 있음)
    @Builder.Default
    @OneToMany(mappedBy = "storeInfo",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

}
