package com.example.bank_project.repository;

import com.example.bank_project.entities.TrxEntity;
import com.example.bank_project.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
