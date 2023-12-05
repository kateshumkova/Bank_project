package com.example.bank_project.mappers;

import com.example.bank_project.dtos.AccountDto;
import com.example.bank_project.dtos.UserDto;
import com.example.bank_project.entities.AccountEntity;
import com.example.bank_project.entities.UserEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // @Mapping(source = "firstName", target = "n")
    UserDto toDto(UserEntity entity);
    UserEntity toEntity(UserDto userDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target ="id", ignore = true)
    @Mapping(target ="username", ignore = true)
    void updateEntity(@MappingTarget UserEntity userEntity, UserDto userDto);
}
