package com.example.bank_project.controller;

import com.example.bank_project.dtos.*;
import com.example.bank_project.entities.AgreementEntity;
import com.example.bank_project.service.AgreementService;
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
@RequestMapping("/api/agreement")
@RequiredArgsConstructor
public class AgreementController {
    private final AgreementService agreementService;

    @Operation(summary = "Получить список всех договоров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Договоры найдены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgreementDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Договоры не найдены",
                    content = @Content)})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping("/")
    public List<AgreementDto> getAll() {
        List<AgreementDto> agreements = agreementService.getAll();
        return agreements;
    }

    @Operation(summary = "Получить договоры по id клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Договоры найдены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgreementDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Договоры не найдены",
                    content = @Content)})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping("/search")
    public List<AgreementDto> getByClientId(@Parameter(description = "id клиента, по которому ведется поиск договоров", example = "2") @RequestParam Long id) {
        return agreementService.findByClientId(id);
    }

    @Operation(summary = "Создать запись о новом договоре")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Договор создан",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgreementDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Договор не создан",
                    content = @Content)})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping("/")
    public CreateAgreementResponse add(@RequestBody CreateAgreementRequest createAgreementRequest) {
        return agreementService.createAgreement(createAgreementRequest);
    }

    @Operation(summary = "Обновить данные договора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные договора обновлены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgreementDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Данные договора не обновлены",
                    content = @Content)})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PutMapping("/{id}")
    public AgreementEntity update(@Parameter(description = "id договора, который надо обновить", example = "2") @PathVariable Long id, @RequestBody AgreementDto agreementDto) {
        return agreementService.updateAgreement(id, agreementDto);
    }

    @Operation(summary = "Удалить данные о договоре")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные договора удалены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgreementDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Данные договора не удалены",
                    content = @Content)})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "id договора, который надо удалить", example = "2") @PathVariable Long id) {
        agreementService.deleteAgreement(id);
    }

}
