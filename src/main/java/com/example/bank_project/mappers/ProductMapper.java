package com.example.bank_project.mappers;

import com.example.bank_project.dtos.AccountDto;
import com.example.bank_project.dtos.ProductDto;
import com.example.bank_project.entities.AccountEntity;
import com.example.bank_project.entities.ProductEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    // @Mapping(source = "firstName", target = "n")
    ProductDto toDto(ProductEntity entity);
    ProductEntity toEntity(ProductDto productDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target ="id", ignore = true)
    @Mapping(target ="name", ignore = true)
    @Mapping(target ="currencyCode", ignore = true)
    @Mapping(target ="interestRate", ignore = true)
    @Mapping(target ="limitMin", ignore = true)
    @Mapping(target ="limitMax", ignore = true)
    void updateEntity(@MappingTarget ProductEntity productEntity, ProductDto productDto);
}
