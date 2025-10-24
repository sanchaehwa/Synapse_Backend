package com.synapse.synapse.domain.user.entity;

import com.synapse.synapse.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name="speech_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpeechData extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob //제약 없는 txt
    @Column(nullable = false)
    private String speechText;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",unique=true)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_user_id")
    private CreateQRUser qrUser;

}
