package com.example.achar.controller;

import com.example.achar.exception.InvalidException;
import com.example.achar.model.users.Client;
import com.example.achar.model.users.Technician;
import com.example.achar.service.TechnicianService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/Technician")
public class TechnicianController {

    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @PostMapping("/register")
    public String register(@RequestBody Technician technician) throws InvalidException {
        technicianService.createTechnician(technician);
        return "ok";
    }

    @PostMapping("/logIn")
    public Technician logIn(@RequestBody Technician technician){
        technicianService.signIn(technician.getEmail() , technician.getPass());
        return technician;
    }
}
