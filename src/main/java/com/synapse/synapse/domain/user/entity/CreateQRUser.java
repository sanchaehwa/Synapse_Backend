package com.synapse.synapse.domain.user.entity;

import com.synapse.synapse.domain.order.entity.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "create_qr_user")
@NoArgsConstructor
@AllArgsConstructor
public class CreateQRUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //QR 생성 App 설치시 자동 생성되는 UUID
    @Column(unique = true, nullable = false)
    private String uuid;

    //복원용 - 전화번호 인증 선택사항
    @Column(unique = true)
    private String phoneNumber;

    @Builder.Default
    @Column
    private boolean isPhoneVerified = false;

    //인증 번호
    private String verificationCode;

    @OneToOne(mappedBy = "qrUser", fetch = FetchType.LAZY, cascade =  CascadeType.ALL, orphanRemoval = true)
    private SpeechData speechData;

    @OneToMany(mappedBy = "qrUser", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
}
