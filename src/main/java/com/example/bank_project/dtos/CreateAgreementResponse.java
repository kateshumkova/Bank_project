package com.example.bank_project.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ДТО договора")
public class CreateAgreementResponse {
    private ProductDto productDto;
    private ClientDto clientDto;
    private AgreementDto agreementDto;
    private AccountDto accountDto;
    private ManagerDto managerDto;
}
