package com.example.bank_project.service.impl;

import com.example.bank_project.dtos.ClientDto;
import com.example.bank_project.dtos.ManagerDto;
import com.example.bank_project.entities.ClientEntity;
import com.example.bank_project.entities.ManagerEntity;
import com.example.bank_project.exception.NotFoundException;
import com.example.bank_project.exception.ValidationException;
import com.example.bank_project.mappers.ManagerMapper;
import com.example.bank_project.repository.ManagerRepository;
import com.example.bank_project.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    @Override
    public List<ManagerDto> getAll() {

        return managerRepository.findAll().stream()
                .map(managerMapper::toDto)
                .toList();
    }

    @Override
    public ManagerDto getById(Long id) {
        Optional<ManagerEntity> optManagerEntity = managerRepository.findById(id);
        if (optManagerEntity.isPresent()) {
            return managerMapper.toDto(optManagerEntity.get());
        } else {
            log.info("Manager id ={} is not found", id);
            throw new NotFoundException("Manager not found");
        }
    }

    @Override
    public List<ManagerDto> findByLastName(String lastName) {
        List<ManagerEntity> managerEntities = managerRepository.findByLastName(lastName);
        if (managerEntities.isEmpty()) {
            log.info("Manager with lastName ={} is not found", lastName);
            throw new NotFoundException("Manager with this lastName not found");
        } else {
            return managerEntities.stream()
                    .map(managerMapper::toDto)
                    .toList();
        }
    }

    @Override
    public ManagerDto createManager(ManagerDto managerDto) {
        ManagerEntity savedManager = managerRepository.save(managerMapper.toEntity(managerDto));
        log.info("Created and saved manager with ID= {}", savedManager.getId());
        return managerMapper.toDto(savedManager);
    }

    @Override
    public ManagerEntity updateManager(Long id, ManagerDto managerDto) {
        Optional<ManagerEntity> optManagerEntity = managerRepository.findById(id);
        if (optManagerEntity.isPresent()) {
            ManagerEntity managerEntity = optManagerEntity.get();
            managerMapper.updateEntity(managerEntity, managerDto);
            managerRepository.save(managerEntity);
            log.info("Manager with ID {} is updated", id);
            return managerEntity;
        }
        throw new NotFoundException("Manager " + id + "cannot be updated, " +
                "id is not found");
    }

    @Override
    public void deleteManager(Long id) {
        Optional<ManagerEntity> optManagerEntity = managerRepository.findById(id);
        if (optManagerEntity.isPresent()) {
            managerRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Manager " + id + "not found");
    }

}

