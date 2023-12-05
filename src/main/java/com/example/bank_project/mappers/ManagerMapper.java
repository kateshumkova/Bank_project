package com.example.bank_project.mappers;

import com.example.bank_project.dtos.AccountDto;
import com.example.bank_project.dtos.ManagerDto;
import com.example.bank_project.entities.AccountEntity;
import com.example.bank_project.entities.ManagerEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
   // @Mapping(source = "firstName", target = "n")
    ManagerDto toDto(ManagerEntity entity);
    ManagerEntity toEntity(ManagerDto managerDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target ="id", ignore = true)
    void updateEntity(@MappingTarget ManagerEntity managerEntity, ManagerDto managerDto);
}
