package com.example.bank_project.service.impl;

import com.example.bank_project.dtos.ClientDto;
import com.example.bank_project.dtos.ProductDto;
import com.example.bank_project.entities.ClientEntity;
import com.example.bank_project.entities.ProductEntity;
import com.example.bank_project.exception.NotFoundException;
import com.example.bank_project.exception.ValidationException;
import com.example.bank_project.mappers.ClientMapper;
import com.example.bank_project.mappers.ProductMapper;
import com.example.bank_project.repository.ClientRepository;
import com.example.bank_project.repository.ProductRepository;
import com.example.bank_project.service.ClientService;
import com.example.bank_project.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public boolean validateOptProduct(Optional<ProductEntity> optProductEntity) {

        if (optProductEntity.isEmpty()) {
            throw new ValidationException("No such product");
        }
        return true;
    }
    @Override
    public List<ProductDto> getAll() {

        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto getById(Long id) {
        Optional<ProductEntity> optProductEntity = productRepository.findById(id);
        if (optProductEntity.isPresent()) {
            return productMapper.toDto(optProductEntity.get());
        } else {
            throw new NotFoundException("Product " + id + " is not found");
        }
    }

    @Override
    public List<ProductDto> findByName(String name) {
        List<ProductEntity> productEntities = productRepository.findByName(name);
        if (productEntities.isEmpty()) {
            throw new NotFoundException("Product with name" + name + " is not found");
        } else {
            return productEntities.stream()
                    .map(productMapper::toDto)
                    .toList();
        }
    }

    @Override
    public ProductDto createProduct(ProductDto clientDto) {
        ProductEntity savedClient = productRepository.save(productMapper.toEntity(clientDto));
        log.info("Created and saved product with ID= {}", savedClient.getId());
        return productMapper.toDto(savedClient);
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductDto productDto) {
        Optional<ProductEntity> optProductEntity = productRepository.findById(id);
        if (optProductEntity.isPresent()) {
            ProductEntity productEntity = optProductEntity.get();
            productMapper.updateEntity(productEntity, productDto);
            productRepository.save(productEntity);
            log.info("Product with ID {} is updated", id);
            return productEntity;
        }
        throw new NotFoundException("Product cannot be updated, " + id + " is not found");

    }

    @Override
    public void deleteProduct(Long id) {
        Optional<ProductEntity> optProductEntity = productRepository.findById(id);
        if (optProductEntity.isPresent()) {
            productRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Product " + id + " is not found");
    }
}