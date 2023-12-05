package com.example.bank_project.controller;

import com.example.bank_project.dtos.AgreementDto;
import com.example.bank_project.dtos.ClientDto;
import com.example.bank_project.dtos.ManagerDto;
import com.example.bank_project.dtos.ProductDto;
import com.example.bank_project.entities.ClientEntity;
import com.example.bank_project.entities.ProductEntity;
import com.example.bank_project.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_USER')")
    @Operation(summary = "Получить список всех продуктов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукты найдены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Продукты не найдены",
                    content = @Content)})
    @GetMapping("/")
    public List<ProductDto> getAll() {
        List<ProductDto> products = productService.getAll();
        return products;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_USER')")
    @Operation(summary = "Получить продукты по названию")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукты найдены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Продукты не найдены",
                    content = @Content)})
    @GetMapping("/search")
    public List<ProductDto> getByName(@Parameter(description = "Название продуктов, по которому ведется поиск", example = "Вклад пенсионный") @RequestParam String name) {
        return productService.findByName(name);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_USER')")
    @Operation(summary = "Получить продукт по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Продукт не найден",
                    content = @Content)})
    @GetMapping("/{id}")
    public ProductDto getById(@Parameter(description = "id продукта, по которому ведется поиск", example = "2") @PathVariable Long id) {
        return productService.getById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @Operation(summary = "Создать запись о новом продукте")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт создан",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Продукт не создан",
                    content = @Content)})
    @PostMapping("/")
    public ProductDto add(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @Operation(summary = "Обновить данные продукта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные продукта обновлены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Данные продукта не обновлены",
                    content = @Content)})
    @PutMapping("/{id}")
    public ProductEntity update(@Parameter(description = "id продукта, который надо обновить", example = "2") @PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @Operation(summary = "Удалить данные о продукте по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные о продукте удалены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Данные о продукте не удалены",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "id продукта, который надо удалить", example = "2") @PathVariable Long id) {
        productService.deleteProduct(id);
    }


}
