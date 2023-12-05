package com.example.bank_project.mappers;

import com.example.bank_project.dtos.UserDto;
import com.example.bank_project.dtos.UserRoleDto;
import com.example.bank_project.entities.UserEntity;
import com.example.bank_project.entities.UserRoleEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    // @Mapping(source = "firstName", target = "n")
    UserRoleDto toDto(UserRoleEntity userRoleEntity);
    UserRoleEntity toEntity(UserRoleDto userRoleDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

   // @Mapping(target ="rolesId", ignore = true)
    //@Mapping(target ="users_id", ignore = true)
    void updateEntity(@MappingTarget UserRoleEntity userRoleEntity, UserRoleDto userRoleDto);
}
