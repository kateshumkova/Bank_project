package com.example.bank_project.service;
import com.example.bank_project.dtos.ClientDto;
import com.example.bank_project.dtos.ManagerDto;
import com.example.bank_project.entities.ClientEntity;
import com.example.bank_project.entities.ManagerEntity;

import java.util.List;

public interface ManagerService {
    List<ManagerDto> getAll();
    ManagerDto getById(Long id);
    List<ManagerDto> findByLastName(String lastName);
    ManagerDto createManager(ManagerDto managerDto);
    ManagerEntity updateManager(Long id, ManagerDto managerDto);
    void deleteManager(Long id);

}
