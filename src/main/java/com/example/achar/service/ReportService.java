package com.example.achar.service;

import com.example.achar.dto.PayedDto;
import com.example.achar.exception.InvalidDateException;
import com.example.achar.exception.InvalidMoneyException;
import com.example.achar.exception.InvalidOutPutException;
import com.example.achar.model.order.Ordered;
import com.example.achar.model.order.OrderedStatus;
import com.example.achar.model.users.Client;
import com.example.achar.model.users.TecStatus;
import com.example.achar.model.users.Technician;
import com.example.achar.repository.ReportRepo;
import com.example.achar.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    final ReportRepo reportRepo;
    final TechnicianService technicianService;
    final ClientService clientService;
    public ReportService(ReportRepo orderedRepo, TechnicianService technicianService, ClientService clientService) {
        this.reportRepo = orderedRepo;
        this.technicianService = technicianService;
        this.clientService = clientService;
    }

    public void createOrder(Ordered ordered){
        if (ordered.getUnderService() == null)
            throw new InvalidOutPutException();
        if (ordered.getPrice() < ordered.getUnderService().getPrices()){
            throw new InvalidMoneyException();
        }
        if (ordered.getDate() < Utils.Date_today){
            throw new InvalidDateException();
        }
        if (ordered.getClient() == null){
            throw new InvalidOutPutException();
        }
        ordered.setOrderedStatus(OrderedStatus.WAITINGFOROFFERED);
        reportRepo.save(ordered);
    }

    public List<Ordered> readLogInClientOrder(long id){
        if (reportRepo.readOrderedByClientId(id) == null){
            throw new InvalidOutPutException();
        }else {
            return reportRepo.readOrderedByClientId(id);
        }
    }

    public Optional<Ordered> readById(Long id){
        return reportRepo.readOrderedById(id);
    }

    public List<Ordered> readSuitableTech(Long id){
        if (technicianService.readById(id).getTecStatus().equals(TecStatus.ACTIVE) && !reportRepo.findSuitable(id).isEmpty()){
            return reportRepo.findSuitable(id);
        }else
            throw new InvalidOutPutException();
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

    public void isStarted(Long id){
        Optional<Ordered> ordered = readById(id);
        if (!ordered.isPresent()){
            throw new InvalidOutPutException();
        }else{
            ordered.get().setOrderedStatus(OrderedStatus.STARTED);
            ordered.get().setStartedTime(10);
            reportRepo.save(ordered.get());
        }
    }

    public void isDone(Long id){
        Optional<Ordered> ordered = readById(id);
        if (!ordered.isPresent() || !ordered.get().getOrderedStatus().equals(OrderedStatus.STARTED)){
            throw new InvalidOutPutException();
        }else {
            ordered.get().setOrderedStatus(OrderedStatus.DONE);
            ordered.get().setFinishTime(20);
            reportRepo.save(ordered.get());

        }
    }

    public void isPayed(Long id , PayedDto payedDto) {
        ArrayList<String> text = new ArrayList<>();
        Optional<Ordered> ordered = readById(id);
        if (!ordered.isPresent() || !ordered.get().getOrderedStatus().equals(OrderedStatus.DONE)) {
            throw new InvalidOutPutException();
        } else {
            ordered.get().setOrderedStatus(OrderedStatus.PAYED);
            Technician technician = ordered.get().getTechnician();
            Long salary = technician.getMoney();
            technician.setMoney(salary + ordered.get().getPrice());
            Client client = ordered.get().getClient();
            if (client.getMoney() - ordered.get().getPrice() < 0){
                throw new InvalidMoneyException();
            }
            client.setMoney(client.getMoney() - ordered.get().getPrice());
            if (payedDto.getPoint() >= 0 && payedDto.getPoint() <= 5){
                double pointTotal = technician.getPoint();
                if (ordered.get().getFinishTime() - ordered.get().getStartedTime() > ordered.get().getTime()){
                    pointTotal = pointTotal - (ordered.get().getFinishTime() - ordered.get().getStartedTime() - ordered.get().getTime()) + payedDto.getPoint();
                }
                technician.setPoint(pointTotal);
                if (technician.getPoint() < 0){
                    technician.setTecStatus(TecStatus.INACTIVE);
                }
//                technician.getReviews().add(payedDto.getText());
//                System.out.println(technician.getReviews().get(2));
                technicianService.create(technician);
                clientService.create(client);
            }else{
                throw new InvalidOutPutException();
            }
        }
    }
}
