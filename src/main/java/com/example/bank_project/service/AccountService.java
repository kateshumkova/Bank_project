package com.example.bank_project.service;

import com.example.bank_project.dtos.CreateAgreementRequest;
import com.example.bank_project.dtos.AccountDto;
import com.example.bank_project.entities.AccountEntity;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAll();
    AccountDto getById(Long id);
    List<AccountDto> findByName(String name);
    AccountEntity createAccount();
    AccountEntity updateAccount(Long id, AccountDto accountDto);
    void deleteAccount(Long id);
}
