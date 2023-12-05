package com.example.bank_project.dtos;

import com.example.bank_project.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@Schema(description = "ДТО Договора")
public class AgreementDto {
    @Schema(description = "Это id договора", example = "2")
    private Long id;
    @Schema(description = "id Клиента,с которым заключен договор", example = "2")
    private Long clientId;
    @Schema(description = "id счета, который открыт для этого договора", example = "3")
    private Long accountId;
    @Schema(description = "id продукта, который является предметом договора", example = "3")
    private Long productId;
    @Schema(description = "id менеджера, который ведет договор", example = "3")
    private Long managerId;
    @Schema(description = "Годовой размер процентной ставки по продукту", example = "6,5")
    private double interestRate;
    @Schema(description = "Статус договора Active or inactive", example = "ACTIVE")
    //private int status;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Schema(description = "Сумма договора", example = "10000000")
    private BigDecimal sum;
}

