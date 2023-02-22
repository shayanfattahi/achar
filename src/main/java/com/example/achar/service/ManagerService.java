package com.example.achar.service;

import com.example.achar.exception.DuplicateUserException;
import com.example.achar.exception.InvalidOutPutException;
import com.example.achar.model.services.Services;
import com.example.achar.model.services.UnderService;
import com.example.achar.model.users.TecStatus;
import com.example.achar.model.users.Technician;
import org.springframework.stereotype.Service;
import java.util.Set;

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
        }else
            throw new InvalidOutPutException();
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
        if(underService == null){
            throw new InvalidOutPutException();
        }else {
            underService.setText(text);
            underService.setPrices(money);
            underServicesService.createUnderServices(underService);
        }
    }

    public void deleteService(String name){
        servicesService.deleteService(name);
    }

    public void addTechnicianToUnderService(String email , String name){
        Technician technician = technicianService.findByEmail(email);
        UnderService underService = underServicesService.readByName(name);
        if (technician.getTecStatus().equals(TecStatus.ACTIVE)) {
            Set<UnderService> underServices = technician.getUnderServices();
            underServices.add(underService);
            Set<Technician> technicians = underService.getTechnician();
            technicians.add(technician);
            technician.setUnderServices(underServices);
            underService.setTechnician(technicians);
            technicianService.updateTechnician(technician);
            underServicesService.createUnderServices(underService);
        }else
            throw new InvalidOutPutException();
    }

    public void deleteTechnicianAndUnderService(String email , String name){
//        Technician technician = technicianService.findByEmail(email);
        UnderService underService = underServicesService.readByName(name);
        underService.getTechnician().forEach((technician1 -> {
            if (technician1.getEmail().equals(email)) {
                underServicesService.delete(underService);
                technicianService.delete(technician1);

            }
        }));
    }
}
