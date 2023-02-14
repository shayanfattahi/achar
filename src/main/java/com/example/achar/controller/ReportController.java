package com.example.achar.controller;

import com.example.achar.model.order.Ordered;
import com.example.achar.service.ClientService;
import com.example.achar.service.ReportService;
import com.example.achar.service.SubjobService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Controller
@RequestMapping("/Ordered")
public class ReportController {
    private final SubjobService subjobService;
    private final ReportService reportService;
    private final ClientService clientService;

    public ReportController(SubjobService subjobService, ReportService reportService, ClientService clientService) {
        this.subjobService = subjobService;
        this.reportService = reportService;
        this.clientService = clientService;
    }

    @PostMapping("/createOrder/{clientId}/{underServiceId}")
    public void createOrder(@RequestBody Ordered ordered, @PathVariable String clientId , @PathVariable Long underServiceId){
        ordered.setClient(clientService.findByEmail(clientId));
        ordered.setUnderService(subjobService.readById(underServiceId));
        reportService.createOrder(ordered);
    }

    @GetMapping("/readLogInClientOrder/{clientId}")
    public Optional<Ordered> readLogInClientOrder(@PathVariable Long clientId){
        return reportService.readLogInClientOrder(clientId);
    }

    @GetMapping("/readSuitableTech/{technicianId}")
    public List<Ordered> readSuitableTech(@PathVariable Long technicianId){
        return reportService.readSuitableTech(technicianId);
    }

    @GetMapping("/makeIsDone/{id}/{point}")
    public void makeIsDone(@PathVariable Long id , double point){
        reportService.makeIsDone(id , point);
    }

}
