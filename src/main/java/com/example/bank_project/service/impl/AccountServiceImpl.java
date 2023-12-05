package com.example.bank_project.service.impl;

import com.example.bank_project.dtos.CreateAgreementRequest;
import com.example.bank_project.dtos.AccountDto;
import com.example.bank_project.entities.AccountEntity;
import com.example.bank_project.entities.ClientEntity;
import com.example.bank_project.entities.ProductEntity;
import com.example.bank_project.enums.Status;
import com.example.bank_project.exception.NotFoundException;
import com.example.bank_project.mappers.AccountMapper;
import com.example.bank_project.mappers.AgreementMapper;
import com.example.bank_project.mappers.ClientMapper;
import com.example.bank_project.repository.AccountRepository;
import com.example.bank_project.repository.AgreementRepository;
import com.example.bank_project.repository.ClientRepository;
import com.example.bank_project.repository.ProductRepository;
import com.example.bank_project.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<AccountDto> getAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toDto)
                .toList();
    }

    @Override
    public AccountDto getById(Long id) {
        Optional<AccountEntity> optAccountEntity = accountRepository.findById(id);
        if (optAccountEntity.isPresent()) {
            return accountMapper.toDto(optAccountEntity.get());
        } else {
            throw new NotFoundException("Account id = " + id + " is not found");
        }
    }

    @Override
    public List<AccountDto> findByName(String name) {
        List<AccountEntity> accountEntities = accountRepository.findByName(name);
        if (accountEntities.isEmpty()) {
            throw new NotFoundException("Account with name = " + name + " is not found");
        } else {
            return accountEntities.stream()
                    .map(accountMapper::toDto)
                    .toList();
        }
    }

    @Transactional
    public AccountEntity createAccount() {

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(new BigDecimal(0));
        accountEntity.setName("Name");//productEntity.getName() + "_" + clientEntity.getId());
        accountEntity.setStatus(Status.ACTIVE);

        AccountEntity savedAccountEntity = accountRepository.saveAndFlush(accountEntity);
        log.info("Account with ID {} is created ", savedAccountEntity.getId());
        return savedAccountEntity;
    }

    @Override
    public AccountEntity updateAccount(Long id, AccountDto accountDto) {
        Optional<AccountEntity> optAccountEntity = accountRepository.findById(id);
        if (optAccountEntity.isPresent()) {
            AccountEntity accountEntity = optAccountEntity.get();
            accountMapper.updateEntity(accountEntity, accountDto);
            accountRepository.save(accountEntity);
            log.info("Account with ID {} is updated ", id);
            return accountEntity;
        }
        throw new NotFoundException("Account id = " + id + " ,cannot be updated, id is not found");

    }

    @Override
    public void deleteAccount(Long id) {
        Optional<AccountEntity> optAccountEntity = accountRepository.findById(id);
        if (optAccountEntity.isPresent()) {
            AccountEntity accountEntity = optAccountEntity.get();
            accountEntity.setStatus(Status.INACTIVE);
            accountRepository.save(accountEntity);
            log.info("Status of account id = {} is changed to inactive or 0", id);
            return;
        }
        throw new NotFoundException("Account id = " + id + " is not found");

    }

}
