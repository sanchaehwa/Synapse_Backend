package com.synapse.synapse.domain.admin.kiosk_management.option.entity;


import com.synapse.synapse.domain.admin.Admin;
import com.synapse.synapse.domain.admin.kiosk_management.menu.entity.KioskMenu;
import com.synapse.synapse.domain.admin.kiosk_management.menu.model.PlatformType;
import com.synapse.synapse.domain.admin.qrcode_management.entity.QrcodeMenu;
import com.synapse.synapse.global.domain.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter
@Builder
@Table(name = "option_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // optionCategoryId

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kiosk_menu_id")
    private KioskMenu kioskMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qrcode_menu_id")
    private QrcodeMenu qrcodeMenu;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private PlatformType platformType;

    @Builder.Default
    @OneToMany(mappedBy = "optionCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptionItem> optionItems = new ArrayList<>();
}
