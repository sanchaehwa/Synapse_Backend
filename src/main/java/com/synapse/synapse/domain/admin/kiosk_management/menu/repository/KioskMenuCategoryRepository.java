package com.synapse.synapse.domain.admin.kiosk_management.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.synapse.synapse.domain.admin.kiosk_management.menu.entity.Category;

@Repository
public interface KioskMenuCategoryRepository extends JpaRepository<Category, Long> {

	@Query("SELECT DISTINCT k.category FROM KioskMenu k WHERE k.admin.storeName = :storeName")
	List<Category> getAllCategoriesByStoreName(@Param("storeName") String storeName);

}
