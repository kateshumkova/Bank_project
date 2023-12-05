package com.example.bank_project.controller;

import com.example.bank_project.dtos.CreateAgreementRequest;
import com.example.bank_project.dtos.AccountDto;

import com.example.bank_project.entities.AccountEntity;

import com.example.bank_project.service.AccountService;

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
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @Operation(summary = "Получить список всех счетов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Счета найдены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Счета не найдены",
                    content = @Content)})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping("/")
    public List<AccountDto> getAll() {
        List<AccountDto> accounts = accountService.getAll();
        return accounts;
    }

    @Operation(summary = "Получить счета по названию")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Счет найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Счет не найден",
                    content = @Content)})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_USER')")
    @GetMapping("/search")
    public List<AccountDto> getByName(@Parameter(description = "Название счета", example = "2") @RequestParam String name) {
        return accountService.findByName(name);
    }

    @Operation(summary = "Получить счет по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Счет найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Счет не найден",
                    content = @Content)})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_USER')")
    @GetMapping("/{id}")
    public AccountDto getById(@Parameter(description = "id счета, по которому ведется поиск", example = "2") @PathVariable Long id) {
        return accountService.getById(id);
    }

    @Operation(summary = "Создать запись о новом аккаунте")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Аккаунт добавлен",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Аккаунт не добавлен",
                    content = @Content)})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping("/")
    public AccountEntity add(@RequestBody CreateAgreementRequest accountAgreementDto) {
        return accountService.createAccount();
    }

    @Operation(summary = "Обновить данные счета")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные счета обновлены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Данные счета не обновлены",
                    content = @Content)})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PutMapping("/{id}")
    public AccountEntity update(@Parameter(description = "id счета, который надо обновить", example = "2") @PathVariable Long id, @RequestBody AccountDto accountDto) {

        return accountService.updateAccount(id, accountDto);
    }

    @Operation(summary = "Удалить данные о счете")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные счета удалены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Данные счета не удалены",
                    content = @Content)})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "id счета, который надо удалить", example = "2") @PathVariable Long id) {
        accountService.deleteAccount(id);
    }
}

