package com.example.achar.service;

import com.example.achar.exception.InvalidDateException;
import com.example.achar.exception.InvalidMoneyException;
import com.example.achar.exception.InvalidOutPutException;
import com.example.achar.model.order.Ordered;
import com.example.achar.model.order.OrderedStatus;
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

    public void createOrder(Ordered ordered){
        if (ordered.getPrice() < ordered.getUnderService().getPrices()){
            throw new InvalidMoneyException();
        }
        if (ordered.getDate() < Utils.Date_today){
            throw new InvalidDateException();
        }
        ordered.setOrderedStatus(OrderedStatus.WAITINGFOROFFERED);
        reportRepo.save(ordered);
    }

    public Optional<Ordered> readLogInClientOrder(long id){
        if (reportRepo.readOrderedByClientId(id) == null){
            throw new InvalidOutPutException();
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
        ordered.get().setOrderedStatus(OrderedStatus.DONE);
        Technician technician = ordered.get().getTechnician();
        Long salary = technician.getMoney();
        technician.setMoney(salary + ordered.get().getPrice());
        double point = technician.getPoint();
        technician.setPoint((point + pointN) / 2);
        reportRepo.save(ordered.get());
        technicianService.create(technician);
    }
}
