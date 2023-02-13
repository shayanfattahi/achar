package com.example.achar.controller;

import com.example.achar.exception.InvalidException;
import com.example.achar.model.Offered;
import com.example.achar.service.OfferedService;
import com.example.achar.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/Offered")
public class OfferedController {

    private final OfferedService offeredService;
    private final ReportService reportService;

    public OfferedController(OfferedService offeredService, ReportService reportService) {
        this.offeredService = offeredService;
        this.reportService = reportService;
    }

    @PostMapping("/createOffered/{orderId}")
    public void createOffered(@PathVariable Offered offered , @PathVariable Long orderId) throws InvalidException {
        offered.setOrdered(reportService.readById(orderId).get());
        offered.setClient(reportService.readById(orderId).get().getClient());
        offered.setUnderService(reportService.readById(orderId).get().getUnderService());
        offeredService.creatOffered(offered);
    }

    @GetMapping("/offeredSortByPrice/{clientId}")
    public List<Offered> readOfferedSortByPrice(@PathVariable Long clientId) {
        return offeredService.readOfferedSortByPrice(clientId);
    }

    @GetMapping("/offeredSortByTechnician/{clientId}")
    public List<Offered> readTopTechnician(@PathVariable Long clientId) {
        return offeredService.readTopTechnician(clientId);
    }

    @PutMapping("/acceptOffered/{clientId}/{orderId}/{offeredId}")
    public void acceptOffered(@PathVariable Long clientId , @PathVariable Long orderId ,@PathVariable Long offeredId ){
        offeredService.acceptOffered(clientId , orderId , offeredId);
    }
}
