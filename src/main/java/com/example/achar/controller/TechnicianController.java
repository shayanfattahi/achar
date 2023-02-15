package com.example.achar.controller;

import com.example.achar.dto.client.ClientDto;
import com.example.achar.dto.client.ClientMapper;
import com.example.achar.dto.client.GetClientDto;
import com.example.achar.dto.technician.GetTechnicianDto;
import com.example.achar.dto.technician.TechnicianDto;
import com.example.achar.dto.technician.TechnicianMapper;
import com.example.achar.model.users.Client;
import com.example.achar.model.users.TecStatus;
import com.example.achar.model.users.Technician;
import com.example.achar.service.TechnicianService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Controller
@RequestMapping("/Technician")
public class TechnicianController {

    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    private Technician dtoToModelWithMapStruct(TechnicianDto technicianDto) {
        return TechnicianMapper.INSTANCE.dtoToModel(technicianDto);
    }

    private GetTechnicianDto modelToGetDto(Technician technician){
        return TechnicianMapper.INSTANCE.modelToGetDto(technician);
    }

    @PostMapping("/register")
    public String register(@RequestBody TechnicianDto technicianDto){
        technicianDto.setTecStatus(TecStatus.NEW);
        technicianService.createTechnician(dtoToModelWithMapStruct(technicianDto));
        return "ok";
    }

    @PostMapping("/logIn")
    public GetTechnicianDto logIn(@RequestBody Technician technician){
        return modelToGetDto(technicianService.signIn(technician.getEmail() , technician.getPass()).get());
    }

    @PutMapping("/changePass/{email}/{pass}/{passNew}")
    public void changePass(@PathVariable String email , @PathVariable String pass , @PathVariable String passNew ){
        technicianService.changePass(email , pass , passNew);
    }
}
