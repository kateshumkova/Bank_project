package com.example.bank_project.repository;

import com.example.bank_project.dtos.ClientDto;
import com.example.bank_project.entities.ClientEntity;
import com.example.bank_project.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    List<ClientEntity> findByLastName(String lastName);
  //  Optional<ClientEntity> findByUserEntity(UserEntity userEntity);
    Optional<ClientEntity> findById(Long id);

    // @Query(value = "SELECT ce FROM ClientEntity ce where ce.email=:email")
    Optional<ClientEntity> getByEmail(String email);
}