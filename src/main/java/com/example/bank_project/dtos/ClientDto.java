package com.example.bank_project.dtos;

import com.example.bank_project.enums.Status;
import io.micrometer.observation.ObservationFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Schema(description = "Клиентский ДТО")
public class ClientDto {

    @Schema(description = "Это id", example = "2")
    private Long id;

    @Schema(description = "Статус клиента Active - у него есть действующие договоры или inactive - действующих договоров нет", example = "ACTIVE")
    //private int status;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Schema(description = "Имя клиента", example = "Иван")
    private String firstName;
    @Schema(description = "Фамилия клиента", example = "Иванов")
    private String lastName;
    @Schema(description = "Email клиента, уникальное поле", example = "ivanov@gmail.com")
    private String email;
    @Schema(description = "Адрес клиента", example = "Спб, Невский проспект, 10")
    private String address;
    @Schema(description = "Телефон клиента", example = "89028304490")
    private String phone;

}
