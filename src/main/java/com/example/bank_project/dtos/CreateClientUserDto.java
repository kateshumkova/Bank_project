package com.example.bank_project.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ДТО пользователя")

public class CreateClientUserDto {

    @Schema(description = "Имя пользователя", example = "maryPoppins")
    private String username;
    @Schema(description = "Пароль пользователя", example = "Qwerty123")
    private String password;
    @Schema(description = "Роль пользователя", example = "ROLE_USER")
    private String roleName;
    @Schema(description = "id клиента", example = "1")
    private Long clientId;


}
