package com.example.achar.controller;

import com.example.achar.model.services.UnderService;
import com.example.achar.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/Manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/registerService")
    public String registerService(@RequestBody String name){
        managerService.createServices(name);
        return "ok";
    }

    @PostMapping("/registerUnderService")
    public String registerUnderService(@RequestBody UnderService underService){
        managerService.createUnderService(underService);
        return "ok";
    }

    @PutMapping("/updateUnderService")
    public UnderService editUnderServices(@RequestBody UnderService underService){
        managerService.editUnderServices(underService.getName() , underService.getText() , underService.getPrices());
        return underService;
    }

    @GetMapping("/changeStatusToActive/{email}")
    public void changeStatusToActive(@PathVariable String email){
        managerService.changeStatusToActive(email);
    }

}
