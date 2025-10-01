package com.tutorials.ecomm.order.infrastructure.secondary.repository;

import com.tutorials.ecomm.order.infrastructure.secondary.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity,Long> {
}
