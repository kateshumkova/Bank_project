package com.example.bank_project.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "ДТО пользователь-роль")

public class UserRoleDto {
   @Schema(description = "Это id таблицы пользователь - роль", example = "2")
    private Long id;

    @Schema(description = "id роли", example = "1")
    private Long rolesId;

    @Schema(description = "id пользователя", example = "3")
    private Long usersId;

}
