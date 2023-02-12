package com.example.achar.controller;

import com.example.achar.exception.InvalidException;
import com.example.achar.model.services.Services;
import com.example.achar.model.services.UnderService;
import com.example.achar.model.users.Client;
import com.example.achar.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/Manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/registerService")
    public String registerService(@RequestBody String name) throws InvalidException {
        managerService.createServices(name);
        return "ok";
    }

    @PostMapping("/registerUnderService")
    public String registerUnderService(@RequestBody UnderService underService) throws InvalidException {
        managerService.createUnderService(underService);
        return "ok";
    }
}
