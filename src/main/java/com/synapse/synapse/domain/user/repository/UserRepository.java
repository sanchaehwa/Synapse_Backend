package com.synapse.synapse.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synapse.synapse.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
