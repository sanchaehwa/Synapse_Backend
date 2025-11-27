package com.synapse.synapse.domain.admin.signup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synapse.synapse.domain.admin.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	boolean existsByStoreName(String storeName);

}
