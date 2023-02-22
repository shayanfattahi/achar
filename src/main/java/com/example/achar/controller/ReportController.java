package com.example.achar.controller;

import com.example.achar.dto.PayedDto;
import com.example.achar.dto.client.ClientDto;
import com.example.achar.dto.client.ClientMapper;
import com.example.achar.dto.client.GetClientDto;
import com.example.achar.dto.ordered.GetOrderedDto;
import com.example.achar.dto.ordered.OrderedDto;
import com.example.achar.dto.ordered.OrderedMapper;
import com.example.achar.model.order.Ordered;
import com.example.achar.model.users.Client;
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

    private Ordered dtoToModelWithMapStruct(OrderedDto orderedDto) {
        return OrderedMapper.INSTANCE.dtoToModel(orderedDto);
    }

    private List<GetOrderedDto> modelToGetDto(List<Ordered> ordered){
        return OrderedMapper.INSTANCE.modelToGetDto(ordered);
    }

    @PostMapping("/createOrder/{clientId}/{underServiceId}")
    public void createOrder(@RequestBody OrderedDto orderedDto, @PathVariable String clientId , @PathVariable String underServiceId){
        Ordered ordered = dtoToModelWithMapStruct(orderedDto);
        ordered.setClient(clientService.findByEmail(clientId));
        ordered.setUnderService(subjobService.readByName(underServiceId));
        reportService.createOrder(ordered);
    }

    @GetMapping("/readLogInClientOrder/{clientId}")
    public List<GetOrderedDto> readLogInClientOrder(@PathVariable Long clientId){
        return modelToGetDto(reportService.readLogInClientOrder(clientId));
    }

    @GetMapping("/readSuitableTech/{technicianId}")
    public List<Ordered> readSuitableTech(@PathVariable Long technicianId){
        return reportService.readSuitableTech(technicianId);
    }

    @GetMapping("/makeIsDone/{id}/{point}")
    public void makeIsDone(@PathVariable Long id , double point){
        reportService.makeIsDone(id , point);
    }

    @PutMapping("/isStart/{orderedId}")
    public void isStart(@PathVariable Long orderedId){
        reportService.isStarted(orderedId);
    }

    @PutMapping("/isDone/{orderedId}")
    public void isDone(@PathVariable Long orderedId){
        reportService.isDone(orderedId);
    }

    @PutMapping("/isPayed/{orderedId}")
    public void isPayed(@RequestBody PayedDto payedDto , @PathVariable Long orderedId){
        reportService.isPayed(orderedId , payedDto);
    }
}
