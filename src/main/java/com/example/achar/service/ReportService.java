package com.example.achar.service;

import com.example.achar.exception.InvalidException;
import com.example.achar.model.Ordered;
import com.example.achar.model.users.Technician;
import com.example.achar.repository.ReportRepo;
import com.example.achar.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    final ReportRepo reportRepo;
    final TechnicianService technicianService;

    public ReportService(ReportRepo orderedRepo, TechnicianService technicianService) {
        this.reportRepo = orderedRepo;
        this.technicianService = technicianService;
    }

    public void createOrder(Ordered ordered) throws InvalidException {
        if (ordered.getPrice() < ordered.getUnderService().getPrices()){
            throw new InvalidException("gheimate namonaseb");
        }
        if (ordered.getDate() < Utils.Date_today){
            throw new InvalidException("tarikh vase rooz haie gozashte ast");
        }
        reportRepo.save(ordered);
    }

    public Optional<Ordered> readLogInClientOrder(long id) throws InvalidException {
        if (reportRepo.readOrderedByClientId(id) == null){
            throw new InvalidException("null ast");
        }else {
            return reportRepo.readOrderedByClientId(id);
        }
    }

    public Optional<Ordered> indefinitService(long id){
        return reportRepo.readOrderedByAcceptedIsFalseAndClientId(id);
    }

    public Optional<Ordered> acceptedService(long id){
        return reportRepo.readOrderedByAcceptedIsTrueAndClientId(id);
    }

    public Optional<Ordered> doneServices(long id){
        return reportRepo.readOrderedByAcceptedIsTrueAndDonedIsTrueAndClientId(id);
    }

    public Optional<Ordered> readById(Long id){
        return reportRepo.readOrderedById(id);
    }

    public List<Ordered> readSuitableTech(Long id){
        return reportRepo.findSuitable(id);
    }

    public void createOrderByTechnician(Ordered ordered){
        reportRepo.save(ordered);
    }

    public void makeIsDone(Long id , double pointN){
        Optional<Ordered> ordered = readById(id);
        ordered.get().setDoned(true);
        Technician technician = ordered.get().getTechnician();
        Long salary = technician.getMoney();
        technician.setMoney(salary + ordered.get().getPrice());
        double point = technician.getPoint();
        technician.setPoint((point + pointN) / 2);
        reportRepo.save(ordered.get());
        technicianService.create(technician);
    }
}
