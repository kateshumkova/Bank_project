package com.example.bank_project.service.impl;

import com.example.bank_project.dtos.*;
import com.example.bank_project.entities.*;
import com.example.bank_project.exception.NotFoundException;
import com.example.bank_project.mappers.*;
import com.example.bank_project.repository.*;
import com.example.bank_project.service.AccountService;
import com.example.bank_project.service.AgreementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.bank_project.enums.Status.ACTIVE;
import static com.example.bank_project.enums.Status.INACTIVE;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;
    private final AccountService accountService;


    @Override
    public List<AgreementDto> getAll() {
        return agreementRepository.findAll().stream()
                .map(agreementMapper::toDto)
                .toList();
    }

    @Override
    public AgreementDto getById(Long id) {
        Optional<AgreementEntity> optAgreementEntity = agreementRepository.findById(id);
        if (optAgreementEntity.isPresent()) {
            return agreementMapper.toDto(optAgreementEntity.get());
        } else {
            log.info("Agreement id ={} is not found", id);
            throw new NotFoundException("Agreement not found");
        }
    }
    @Override
    public List<AgreementDto> findByClientId(Long id) {
        List<AgreementEntity> agreementEntities = agreementRepository.findByClientId(id);
        if (agreementEntities.isEmpty()) {
            log.info("There is no Agreement for client id ={}", id);
            throw new NotFoundException("Agreement for client id doesn't exist");
        } else {
            return agreementEntities.stream()
                    .map(agreementMapper::toDto)
                    .toList();
        }
    }
    @Override
    public List<AgreementDto> findAllActive() {
        List<AgreementEntity> agreementEntities = agreementRepository.findByStatus(1);
        if (agreementEntities.isEmpty()) {
            log.info("There is no active Agreements");
            throw new NotFoundException("There is no active Agreements");
        } else {
            return agreementEntities.stream()
                    .map(agreementMapper::toDto)
                    .toList();
        }
    }
    @Override
    public List<AgreementDto> findByAccountId(Long id) {
        List<AgreementEntity> agreementEntities = agreementRepository.findByAccountId(id);
        if (agreementEntities.isEmpty()) {
            log.info("There is no Agreement with account id ={}", id);
            throw new NotFoundException("Agreement for this account id doesn't exist");
        } else {
            return agreementEntities.stream()
                    .map(agreementMapper::toDto)
                    .toList();
        }
    }

    @Override
    public List<AgreementDto> findByProductId(Long id) {
        List<AgreementEntity> agreementEntities = agreementRepository.findByProductId(id);
        if (agreementEntities.isEmpty()) {
            log.info("There is no Agreement for product id ={}", id);
            throw new NotFoundException("Agreement with this product id doesn't exist");
        } else {
            return agreementEntities.stream()
                    .map(agreementMapper::toDto)
                    .toList();
        }
    }

    @Override
    public List<AgreementDto> findByManagerId(Long id) {
        List<AgreementEntity> agreementEntities = agreementRepository.findByManagerId(id);
        if (agreementEntities.isEmpty()) {
            throw new NotFoundException("There are no Agreements with this manager id"
                    + id);
        } else {
            return agreementEntities.stream()
                    .map(agreementMapper::toDto)
                    .toList();
        }
    }

    @Transactional
    public CreateAgreementResponse createAgreement(CreateAgreementRequest createAgreementRequest) {

        Optional<ClientEntity> optClientEntity = clientRepository.findById(createAgreementRequest.getClientId());
        Optional<ProductEntity> optProductEntity = productRepository.findById(createAgreementRequest.getProductId());
        Optional<ManagerEntity> optManagerEntity = managerRepository.findById(createAgreementRequest.getManagerId());
        // validateOptional(Optional<ClientEntity>)
        if (optClientEntity.isEmpty() || optProductEntity.isEmpty() || optManagerEntity.isEmpty()) {
            throw new NotFoundException("Some value is empty");
        }
        AgreementEntity agreementEntity = new AgreementEntity();
        ClientEntity clientEntity = optClientEntity.get();
        agreementEntity.setClient(clientEntity);
        ProductEntity productEntity = optProductEntity.get();
        agreementEntity.setProduct(productEntity);
        ManagerEntity managerEntity = optManagerEntity.get();
        agreementEntity.setManager(managerEntity);

        AccountEntity accountEntity = accountService.createAccount();
        agreementEntity.setAccount(accountEntity);

        agreementEntity.setInterestRate(productEntity.getInterestRate());
        agreementEntity.setSum(createAgreementRequest.getSum());
        agreementEntity.setStatus(ACTIVE);
        AgreementEntity savedAgreementEntity = agreementRepository.saveAndFlush(agreementEntity);

        CreateAgreementResponse createAgreementResponse = new CreateAgreementResponse();
        createAgreementResponse.setAgreementDto(agreementMapper.toDto(savedAgreementEntity));
        createAgreementResponse.setClientDto(clientMapper.toDto(clientEntity));
        createAgreementResponse.setAccountDto(accountMapper.toDto(accountEntity));
        createAgreementResponse.setProductDto(productMapper.toDto(productEntity));
        createAgreementResponse.setManagerDto(managerMapper.toDto(managerEntity));

        log.info("Agreement with ID " + savedAgreementEntity.getId() + " is created");

        return createAgreementResponse;
    }


    @Override
    public AgreementEntity updateAgreement(Long id, AgreementDto clientDto) {
        Optional<AgreementEntity> optAgreementEntity = agreementRepository.findById(id);
        if (optAgreementEntity.isPresent()) {
            AgreementEntity clientEntity = optAgreementEntity.get();
            agreementMapper.updateEntity(clientEntity, clientDto);
            agreementRepository.save(clientEntity);
            log.info("Agreement with ID {} is updated ", id);
            return clientEntity;
        }
        throw new NotFoundException("Agreement cannot be updated, " + id + " is not found");

    }

    @Transactional
    @Override
    public void deleteAgreement(Long id) {
        Optional<AgreementEntity> optAgreementEntity = agreementRepository.findById(id);
        if (optAgreementEntity.isPresent()) {
            AgreementEntity agreementEntity = optAgreementEntity.get();
            agreementEntity.setStatus(INACTIVE);
            agreementRepository.save(agreementEntity);
            accountService.deleteAccount(agreementEntity.getAccount().getId());

            log.info("Status of agreement id = {} is changed to inactive or 0 ", id);
            return;
        }
        throw new NotFoundException("Agreement cannot be deleted, " + id + " is not found");

    }


}
