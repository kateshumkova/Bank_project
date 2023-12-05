package com.example.bank_project.controller;

import com.example.bank_project.dtos.AccountDto;
import com.example.bank_project.dtos.CreateAgreementRequest;
import com.example.bank_project.dtos.UserDto;
import com.example.bank_project.entities.AccountEntity;
import com.example.bank_project.service.AccountService;
import com.example.bank_project.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RestController
@RequestMapping("/noauth/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Создать запись о новом пользователе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь добавлен",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Пользователь не добавлен",
                    content = @Content)})
    @PostMapping("/")
    public void add(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }
}
