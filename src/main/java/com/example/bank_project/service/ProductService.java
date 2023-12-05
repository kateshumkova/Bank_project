package com.example.bank_project.service;

import com.example.bank_project.dtos.ClientDto;
import com.example.bank_project.dtos.ProductDto;
import com.example.bank_project.entities.ClientEntity;
import com.example.bank_project.entities.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> getAll();
    ProductDto getById(Long id);
    List<ProductDto> findByName(String name);
    ProductDto createProduct(ProductDto productDto);
    ProductEntity updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
    boolean validateOptProduct(Optional<ProductEntity> optProductEntity);

}
