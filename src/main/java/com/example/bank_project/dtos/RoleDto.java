package com.example.bank_project.dtos;

import com.example.bank_project.entities.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Schema(description = "ДТО роли")

public class RoleDto {
   @Schema(description = "Это id роли", example = "2")
    private Long id;

    @Schema(description = "Название роли", example = "ROLE_MANAGER")
    private String name;
}
