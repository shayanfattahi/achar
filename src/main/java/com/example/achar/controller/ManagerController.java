package com.example.achar.controller;

import com.example.achar.model.services.UnderService;
import com.example.achar.service.JobService;
import com.example.achar.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/Manager")
public class ManagerController {

    private final ManagerService managerService;
    private final JobService jobService;

    public ManagerController(ManagerService managerService, JobService jobService) {
        this.managerService = managerService;
        this.jobService = jobService;
    }

    @PostMapping("/registerService")
    public String registerService(@RequestBody String name){
        managerService.createServices(name);
        return "ok";
    }

    @PostMapping("/registerUnderService/{serviceID}")
    public String registerUnderService(@RequestBody UnderService underService , @PathVariable Long serviceID){
        underService.setServices(jobService.readById(serviceID));
        managerService.createUnderService(underService);
        return "ok";
    }

    @PutMapping("/updateUnderService")
    public UnderService editUnderServices(@RequestBody UnderService underService){
        managerService.editUnderServices(underService.getName() , underService.getText() , underService.getPrices());
        return underService;
    }

    @PutMapping("/changeStatusToActive/{email}")
    public void changeStatusToActive(@PathVariable String email){
        managerService.changeStatusToActive(email);
    }

    @DeleteMapping("/deleteServices/{name}")
    public void deleteService(@PathVariable String name){
        managerService.deleteService(name);
    }

    @PutMapping("/addTechnicianToUnderservice/{email}/{name}")
    public void addTechnicianToUnderservice(@PathVariable String email, @PathVariable String name ){
        managerService.addTechnicianToUnderService(email , name);
    }

    @DeleteMapping("/deleteTechnicianAndUnderService/{email}/{name}")
    public void deleteTechnicianAndUnderService(@PathVariable String email, @PathVariable String name ){
        managerService.deleteTechnicianAndUnderService(email , name);
    }


}
