package com.example.bank_project.service;

import com.example.bank_project.dtos.CreateAgreementRequest;
import com.example.bank_project.dtos.AgreementDto;
import com.example.bank_project.dtos.CreateAgreementResponse;
import com.example.bank_project.entities.*;

import java.util.List;

public interface AgreementService {
    List<AgreementDto> getAll();
    AgreementDto getById(Long id);
    List<AgreementDto> findByClientId(Long id);

   // static List<AgreementDto> findAllActive() {
   //     return null;
  //  }

    List<AgreementDto> findAllActive();

    List<AgreementDto> findByAccountId(Long id);

    List<AgreementDto> findByProductId(Long id);
    List<AgreementDto> findByManagerId(Long id);
    CreateAgreementResponse createAgreement(CreateAgreementRequest createAgreementRequest);
    AgreementEntity updateAgreement(Long id, AgreementDto agreementDto);
    void deleteAgreement(Long id);


}
