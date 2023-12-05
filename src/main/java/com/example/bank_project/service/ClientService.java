package com.example.bank_project.service;

import com.example.bank_project.dtos.ClientDto;
import com.example.bank_project.entities.ClientEntity;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAll();
    ClientDto getById(Long id);
    // ClientDto getByEmail(String email);
    List<ClientDto> findByLastName(String lastName);
    ClientDto createClient(ClientDto clientDto);
    ClientEntity updateClient(Long id, ClientDto clientDto);
    void deleteClient(Long id);


}
