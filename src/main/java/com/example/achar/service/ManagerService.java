package com.example.achar.service;

import com.example.achar.exception.DuplicateUserException;
import com.example.achar.model.services.Services;
import com.example.achar.model.services.UnderService;
import com.example.achar.model.users.TecStatus;
import com.example.achar.model.users.Technician;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    final
    TechnicianService technicianService;
    final
    JobService servicesService;
    final
    SubjobService underServicesService;

    public ManagerService(TechnicianService technicianService, JobService servicesService, SubjobService underServicesService) {
        this.technicianService = technicianService;
        this.servicesService = servicesService;
        this.underServicesService = underServicesService;
    }

    public void changeStatusToActive(String email){
        if (technicianService.findByEmail(email) != null) {
            Technician technician = technicianService.findByEmail(email);
            technician.setTecStatus(TecStatus.ACTIVE);
            technicianService.create(technician);
        }
    }

    public void createServices(String serviceName){
        Services services = new Services();
        if (servicesService.readService(serviceName) == null){
            services.setName(serviceName);
            servicesService.createServices(services);
        }else{
            throw new DuplicateUserException();
        }
    }

    public void createUnderService(UnderService underService){
        if (underServicesService.readByName(underService.getName()) == null){
            underServicesService.createUnderServices(underService);
        }else
            throw new DuplicateUserException();
    }

    public void editUnderServices(String name , String text , long money){
        UnderService underService = underServicesService.readByName(name);
        underService.setText(text);
        underService.setPrices(money);
        underServicesService.createUnderServices(underService);
    }

    public void deleteService(String name){
        servicesService.deleteService(name);
    }
}
