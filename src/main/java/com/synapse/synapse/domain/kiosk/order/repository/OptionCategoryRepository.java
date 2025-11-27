package com.synapse.synapse.domain.kiosk.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synapse.synapse.domain.admin.kiosk_management.option.entity.OptionCategory;

public interface OptionCategoryRepository extends JpaRepository<OptionCategory, Long> {
	Optional<OptionCategory> findByIdAndKioskMenu_MenuName(Long id, String menuName);
}
