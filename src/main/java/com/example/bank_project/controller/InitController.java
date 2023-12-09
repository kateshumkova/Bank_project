package com.example.bank_project.controller;

import com.example.bank_project.dtos.AccountDto;
import com.example.bank_project.dtos.ClientDto;
import com.example.bank_project.dtos.CreateAgreementRequest;
import com.example.bank_project.entities.AccountEntity;
import com.example.bank_project.service.AccountService;
import com.example.bank_project.service.AgreementService;
import com.example.bank_project.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.example.bank_project.enums.Status.ACTIVE;

@RestController
@RequestMapping("/noauth/initdb")
@RequiredArgsConstructor
public class InitController {
    private final AgreementService agreementService;
    private final ClientService clientService;

    @Operation(summary = "Получить список всех счетов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Счета найдены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Счета не найдены",
                    content = @Content)})
    //переделать описание
    @GetMapping("/")
    public void initDb() {
        ClientDto clientDto = new ClientDto();
        clientDto.setStatus(ACTIVE);
        clientDto.setEmail("akkak@gmail");
        ClientDto clientDtoSaved = clientService.createClient(clientDto);
       CreateAgreementRequest createAgreementRequest = new CreateAgreementRequest();
       createAgreementRequest.setSum(BigDecimal.valueOf(1000));
       createAgreementRequest.setClientId(clientDtoSaved.getId());

       // List<AccountDto> accounts = agreementService.createAgreement(createAgreementRequest);

    }

}

