package com.example.achar.controller;

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

    @PostMapping("/register")
    public String register(@RequestBody Technician technician){
        technician.setTecStatus(TecStatus.NEW);
        technicianService.createTechnician(technician);
        return "ok";
    }

    @PostMapping("/logIn")
    public Optional<Technician> logIn(@RequestBody Technician technician){
        return technicianService.signIn(technician.getEmail() , technician.getPass());
    }

    @PutMapping("/changePass/{email}/{pass}/{passNew}")
    public void changePass(@PathVariable String email , @PathVariable String pass , @PathVariable String passNew ){
        technicianService.changePass(email , pass , passNew);
    }
}
