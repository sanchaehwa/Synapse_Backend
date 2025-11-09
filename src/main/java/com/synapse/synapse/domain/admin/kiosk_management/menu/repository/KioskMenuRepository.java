package com.synapse.synapse.domain.admin.kiosk_management.menu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.synapse.synapse.domain.admin.kiosk_management.menu.entity.Category;
import com.synapse.synapse.domain.admin.kiosk_management.menu.entity.KioskMenu;

@Repository
public interface KioskMenuRepository extends JpaRepository<KioskMenu, Long> {
	@Query(" SELECT k FROM KioskMenu k WHERE k.admin.storeName = :storeName AND k.category.id = :categoryId")
	List<KioskMenu> findMenusByStoreName(@Param("storeName") String storeName, @Param("categoryId") Long categoryId);

	@Query(" SELECT k FROM KioskMenu k WHERE k.admin.storeName = :storeName AND k.id = :id ")
	Optional<KioskMenu> findByIdAndStoreName(@Param("storeName") String storeName, @Param("id") Long id);

	boolean existsByCategoryId(Long id);

	@Query("SELECT DISTINCT k.category FROM KioskMenu k WHERE k.admin.storeName = :storeName")
	List<Category> getAllCategoriesByStoreName(@Param("storeName") String storeName);
}
