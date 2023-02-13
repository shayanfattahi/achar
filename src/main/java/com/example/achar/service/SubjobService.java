package com.example.achar.service;

import com.example.achar.model.services.UnderService;
import com.example.achar.repository.UnderServicesRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjobService {

    private final UnderServicesRepo underServicesRepo;
    public SubjobService(UnderServicesRepo underServicesRepo) {
        this.underServicesRepo = underServicesRepo;
    }

    public void createUnderServices(UnderService underService){
        underServicesRepo.save(underService);
    }

    public UnderService readById(Long id){
        return underServicesRepo.readUnderServiceById(id);
    }

    public UnderService readByName(String name){
        return underServicesRepo.readUnderServiceByName(name);
    }

    public List<UnderService>readByService(Long id){
        return underServicesRepo.readUnderServiceByServicesId(id);
    }
}
