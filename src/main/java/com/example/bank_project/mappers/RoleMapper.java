package com.example.bank_project.mappers;

import com.example.bank_project.dtos.AccountDto;
import com.example.bank_project.dtos.RoleDto;
import com.example.bank_project.entities.AccountEntity;
import com.example.bank_project.entities.RoleEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    // @Mapping(source = "firstName", target = "n")
    RoleDto toDto(RoleEntity entity);
    RoleEntity toEntity(RoleDto roleDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target ="id", ignore = true)
    @Mapping(target ="name", ignore = true)
    void updateEntity(@MappingTarget RoleEntity roleEntity, RoleDto roleDto);
}
