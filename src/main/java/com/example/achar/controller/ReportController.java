package com.example.achar.controller;

import com.example.achar.exception.InvalidException;
import com.example.achar.model.Ordered;
import com.example.achar.service.ReportService;
import com.example.achar.service.SubjobService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/Ordered")
public class ReportController {
    private final SubjobService subjobService;
    private final ReportService reportService;

    public ReportController(SubjobService subjobService, ReportService reportService) {
        this.subjobService = subjobService;
        this.reportService = reportService;
    }

    @PostMapping("/createOrder")
    public void createOrder(@RequestBody Ordered ordered) throws InvalidException {
        reportService.createOrder(ordered);
    }

    @GetMapping("/readLogInClientOrder/{clientId}")
    public void readLogInClientOrder(@PathVariable Long clientId) throws InvalidException {
        reportService.readLogInClientOrder(clientId);
    }

    @GetMapping("/readSuitableTech/{technicianId}")
    public void readSuitableTech(@PathVariable Long technicianId){
        reportService.readSuitableTech(technicianId);
    }

    @GetMapping("/makeIsDone/{id}/{point}")
    public void makeIsDone(@PathVariable Long id , double point){
        reportService.makeIsDone(id , point);
    }

}
