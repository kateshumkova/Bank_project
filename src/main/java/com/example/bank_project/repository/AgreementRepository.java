package com.example.bank_project.repository;

import com.example.bank_project.dtos.ClientDto;
import com.example.bank_project.entities.AgreementEntity;
import com.example.bank_project.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgreementRepository extends JpaRepository<AgreementEntity, Long> {
    List<AgreementEntity> findByClientId(Long id);
    List<AgreementEntity> findByStatus(int status);
    Optional<AgreementEntity> findById(Long id);


    List<AgreementEntity> findByAccountId(Long id);

    List<AgreementEntity> findByProductId(Long id);

    List<AgreementEntity> findByManagerId(Long id);
}

