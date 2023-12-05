package com.example.bank_project.repository;

import com.example.bank_project.entities.RoleEntity;
import com.example.bank_project.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String username);
    Optional<RoleEntity> findById(Long id);
}
