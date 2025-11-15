package com.synapse.synapse.domain.admin.kiosk_management.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.synapse.synapse.domain.admin.kiosk_management.option.entity.OptionCategory;

@Repository
public interface KioskMenuOptionRepository extends JpaRepository<OptionCategory, Long> {

	@Query("""
		    SELECT DISTINCT oc FROM OptionCategory oc
		    LEFT JOIN FETCH oc.menuOptions
		    WHERE oc.kioskMenu.id = :menuId
		""")
	List<OptionCategory> findAllByKioskMenuId(@Param("menuId") Long menuId);

	;
}
