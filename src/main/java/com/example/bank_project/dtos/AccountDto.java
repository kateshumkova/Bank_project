package com.example.bank_project.dtos;

import com.example.bank_project.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@Schema(description = "ДТО счета")

public class AccountDto {
   @Schema(description = "Это id счета", example = "2")
    private Long id;
   // @Schema(description = "id Клиента,с которым заключен договор", example = "2")
  //  private Long clientId;
   @Schema(description = "название счета", example = "Пенсионный вклад 3223")
    private String name;

    @Schema(description = "Статус счета Active или inactive", example = "ACTIVE")
    //private int status;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Schema(description = "Баланс счета, изменяется сразу после проведения транзакции", example = "10000000")
    private BigDecimal balance;
   // @Schema(description = "Валюта счета: RUB 1, EUR 10, USD 7", example = "1")
   // private int currencyCode;
}
