package com.synapse.synapse.domain.admin.qrcode_management.repository;

import static com.synapse.synapse.domain.admin.QAdmin.*;
import static com.synapse.synapse.domain.admin.kiosk_management.option.entity.QOptionCategory.*;
import static com.synapse.synapse.domain.admin.qrcode_management.entity.QQrcodeMenu.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.synapse.synapse.domain.admin.kiosk_management.menu.model.PlatformType;
import com.synapse.synapse.domain.admin.qrcode_management.entity.QrcodeMenu;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QrcodeRepository {

	private final JPAQueryFactory queryFactory;

	//Projections 적용 안한 경우
	public List<QrcodeMenu> findQrMenusByStoreName(String storeName) {
		return queryFactory
			.selectFrom(qrcodeMenu)
			.distinct()
			.join(qrcodeMenu.admin, admin)
			.leftJoin(qrcodeMenu.optionCategories, optionCategory).fetchJoin()
			.where(
				admin.storeName.eq(storeName)
					.and(
						optionCategory.isNull()
							.or(optionCategory.platformType.eq(PlatformType.QRCODE))
					)
			)
			.orderBy(qrcodeMenu.lastModifiedDate.desc()) //	최신순 조회
			.fetch();
	}

}
