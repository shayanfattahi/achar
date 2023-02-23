package com.example.achar.controller;

import com.example.achar.model.PhotoTec;
import com.example.achar.model.users.Technician;
import com.example.achar.service.PhotoTecService;
import com.example.achar.service.TechnicianService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/Photo")
public class PhotoController {

    final PhotoTecService photoTecService;
    final TechnicianService technicianService;

    public PhotoController(PhotoTecService photoTecService, TechnicianService technicianService) {
        this.photoTecService = photoTecService;
        this.technicianService = technicianService;
    }

    @PostMapping("/photo/{techid}")
    public void setPhoto(@ModelAttribute PhotoTec photoTec , @PathVariable Long techid){
        Technician technician = technicianService.readById(techid);
        photoTec.setTechnician(technician);
        photoTecService.insertPhoto(photoTec);
    }
}
