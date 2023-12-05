package com.example.bank_project.service;
import com.example.bank_project.dtos.ClientDto;
import com.example.bank_project.dtos.TrxDto;
import com.example.bank_project.entities.ClientEntity;
import com.example.bank_project.entities.TrxEntity;
import java.util.List;

public interface TrxService {
    List<TrxDto> getAll();
    TrxDto getById(Long id);
    List<TrxDto> findByAccountId(Long id);
    List<TrxDto> findByStatus(int status);
    TrxDto createTrx(TrxDto trxDto);
    TrxEntity updateTrx(Long id, TrxDto trxDto);
    void deleteTrx(Long id);
}
