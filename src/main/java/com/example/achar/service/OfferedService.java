package com.example.achar.service;

import com.example.achar.exception.InvalidDateException;
import com.example.achar.model.Offered;
import com.example.achar.model.Ordered;
import com.example.achar.repository.OfferedRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferedService {
    final OfferedRepo offeredRepo;
    final ReportService reportService;

    public OfferedService(OfferedRepo offeredRepo, ReportService reportService) {
        this.offeredRepo = offeredRepo;
        this.reportService = reportService;
    }

    public Offered readById(Long id){
        return offeredRepo.readOfferedById(id);
    }

    public List<Offered> readLogInClientOffered(long id1 , long id2){
        return offeredRepo.readOfferedByClientIdAndOrderedId(id1 , id2);
    }

    public void creatOffered(Offered offered){
        if (offered.getDate() < offered.getOrdered().getDate()){
            throw new InvalidDateException();
        }else {
            offeredRepo.save(offered);
        }
    }

    public List<Offered> readOfferedSortByPrice(Long id){
        return offeredRepo.findOfferedByClientIdOrderByPriceAsc(id);
    }

    public List<Offered> readTopTechnician(Long id){
        return offeredRepo.readGoodPoint(id);
    }

    public void acceptOffered(Long id1 , Long id2 , Long offeredChoosen){
        List<Offered> offereds = readLogInClientOffered(id1 , id2);
        for (Offered offered: offereds) {
            System.out.println(offered.getId()+") gheimat: "+offered.getPrice());
        }
        Offered offered = offeredRepo.readOfferedById(offeredChoosen);
        Optional<Ordered> ordered = reportService.readById(offered.getOrdered().getId());
        ordered.get().setPrice(offered.getPrice());
        ordered.get().setDate(offered.getDate());
        ordered.get().setAccepted(true);
        ordered.get().setTechnician(offered.getTechnician());
        offered.setAccepted(true);
        offeredRepo.save(offered);
        reportService.createOrderByTechnician(ordered.get());

    }
}
