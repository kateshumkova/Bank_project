package com.example.bank_project.service.impl;

import com.example.bank_project.dtos.TrxDto;
import com.example.bank_project.entities.*;
import com.example.bank_project.enums.Status;
import com.example.bank_project.enums.TrxType;
import com.example.bank_project.exception.NotFoundException;
import com.example.bank_project.mappers.AccountMapper;
import com.example.bank_project.mappers.TrxMapper;
import com.example.bank_project.repository.AccountRepository;
import com.example.bank_project.repository.ClientRepository;
import com.example.bank_project.repository.TrxRepository;
import com.example.bank_project.repository.UserRepository;
import com.example.bank_project.service.TrxService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrxServiceImpl implements TrxService {
    private final TrxRepository trxRepository;
    private final TrxMapper trxMapper;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    @Override
    public List<TrxDto> getAll() {

        return trxRepository.findAll().stream()
                .map(trxMapper::toDto)
                .toList();
    }

    @Override
    public TrxDto getById(Long id) {
        Optional<TrxEntity> optTrxEntity = trxRepository.findById(id);
        if (optTrxEntity.isPresent()) {
            return trxMapper.toDto(optTrxEntity.get());
        } else {
            throw new NotFoundException("Trx " + id + "is not found");
        }
    }

    @Override
    public List<TrxDto> findByAccountId(Long accountId, Authentication authentication) {
        List<TrxEntity> trxEntities = trxRepository.findByAccountId(accountId);
        if (trxEntities.isEmpty()) {
            throw new NotFoundException("There is no trx for this account" + accountId);
        } else {
         /*  // String name = authentication.getName(); //mary
            Optional<UserEntity> optUserEntity = userRepository.findByUsername(authentication.getName());
            if (optUserEntity.isEmpty()) {
                throw new NotFoundException("There is no such username" + authentication.getName());
            } else {
                UserEntity userEntity = optUserEntity.get();
                Optional<ClientEntity> optClientEntity = clientRepository.findByUserEntity(userEntity);
                if (optClientEntity.isEmpty()) {
                    throw new NotFoundException("There is no client with such username" + authentication.getName());
                } else {


//            var author = authentication.getAuthorities();
//            var cred = authentication.getCredentials();
//            var details = authentication.getDetails();
//            var principal = authentication.getPrincipal();
//            var authClass = authentication.getClass();
       */
            return trxEntities.stream()
                            .map(trxMapper::toDto)
                            .toList();
                }
            }
        //}
   // }

    @Override
    public List<TrxDto> findByStatus(int status) {
        List<TrxEntity> trxEntities = trxRepository.findByStatus(status);
        if (trxEntities.isEmpty()) {
            throw new NotFoundException("There is no trx with status" + status);
        } else {
            return trxEntities.stream()
                    .map(trxMapper::toDto)
                    .toList();
        }
    }

    @Transactional
    @Override
    public TrxDto createTrx(TrxDto trxDto) {
        // Optional<TrxEntity> optTrxEntity = trxRepository.getByEmail(trxDto.getEmail());
        //  if (optTrxEntity.isEmpty()) {
        TrxEntity trxEntity = trxMapper.toEntity(trxDto);
        Optional<AccountEntity> optAccountEntity = accountRepository.findById(trxDto.getAccountId());
        AccountEntity accountEntity = optAccountEntity.get();
        BigDecimal balanceBeforeTrx = accountEntity.getBalance();

        //если операция дебитовая  тип 1
        if (trxEntity.getType() == TrxType.DEBIT) {
            accountEntity.setBalance(balanceBeforeTrx.add(trxDto.getAmount()));
        } else {
            //если операция кредитовая  тип 2
            //проверка достаточен ли баланс для проведения операции списания

            BigDecimal amountTrx = trxDto.getAmount();
            if (amountTrx.compareTo(balanceBeforeTrx) <= 0) {
                accountEntity.setBalance(balanceBeforeTrx.subtract(trxDto.getAmount()));
            } else log.info("Balance is not enough to proceed the operation");
        }
        trxEntity.setAccount(accountEntity);

        AccountEntity savedAccountEntity = accountRepository.saveAndFlush(accountEntity);
        TrxEntity savedTrx = trxRepository.save(trxEntity);
        log.info("Created and saved Trx with ID= {}", savedTrx.getId());
        return trxMapper.toDto(savedTrx);

    }

    @Override
    public TrxEntity updateTrx(Long id, TrxDto clientDto) {
        Optional<TrxEntity> optTrxEntity = trxRepository.findById(id);
        if (optTrxEntity.isPresent()) {
            TrxEntity trxEntity = optTrxEntity.get();
            trxMapper.updateEntity(trxEntity, clientDto);
            trxRepository.save(trxEntity);
            log.info("Trx with ID {} is updated", id);
            return trxEntity;
        }
        throw new NotFoundException("Trx cannot be updated," + id + "is not found");

    }

    @Override
    public void deleteTrx(Long id) {
        Optional<TrxEntity> optTrxEntity = trxRepository.findById(id);
        if (optTrxEntity.isPresent()) {
            // trxRepository.deleteById(id);
            TrxEntity trxEntity = optTrxEntity.get();
            trxEntity.setStatus(Status.INACTIVE);
            trxRepository.save(trxEntity);
            return;
        }
        throw new NotFoundException("Trx" + id + "is " +
                "not found");
    }

}
