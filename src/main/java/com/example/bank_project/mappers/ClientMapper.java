package com.example.bank_project.mappers;

import com.example.bank_project.dtos.AccountDto;
import com.example.bank_project.dtos.ClientDto;
import com.example.bank_project.entities.AccountEntity;
import com.example.bank_project.entities.ClientEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    // @Mapping(source = "firstName", target = "n")
    ClientDto toDto(ClientEntity entity);

    ClientEntity toEntity(ClientDto clientDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target ="id", ignore = true)
    void updateEntity(@MappingTarget ClientEntity clientEntity, ClientDto clientDto);
}
