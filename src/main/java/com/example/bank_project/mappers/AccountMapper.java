package com.example.bank_project.mappers;

import com.example.bank_project.dtos.AccountDto;
import com.example.bank_project.dtos.ClientDto;
import com.example.bank_project.dtos.ManagerDto;
import com.example.bank_project.entities.AccountEntity;
import com.example.bank_project.entities.ClientEntity;
import com.example.bank_project.entities.ManagerEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    // @Mapping(source = "firstName", target = "n")
    AccountDto toDto(AccountEntity entity);
    AccountEntity toEntity(AccountDto accountDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target ="id", ignore = true)
    @Mapping(target ="balance", ignore = true)
    void updateEntity(@MappingTarget AccountEntity accountEntity, AccountDto accountDto);
}
