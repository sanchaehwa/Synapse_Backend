package com.synapse.synapse.domain.kiosk.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synapse.synapse.domain.admin.kiosk_management.option.entity.MenuOption;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
	Optional<MenuOption> findByOptionCategoryIdAndOptionName(Long optionCategoryId, String optionName);
}
