package com.example.achar.service;

import com.example.achar.exception.*;
import com.example.achar.model.users.Client;
import com.example.achar.model.users.Technician;
import com.example.achar.repository.TechnicianRepo;
import com.example.achar.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechnicianService {

    private final TechnicianRepo technicianRepo;
    public TechnicianService(TechnicianRepo technicianRepo) {
        this.technicianRepo = technicianRepo;
    }

    public void createTechnician(Technician technician){
        if (technicianRepo.findClientByEmail(technician.getEmail()) != null){
            throw new DuplicateUserException();
        }

        if (!technician.getPass().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
            throw new InvalidPassException();
        }
        if (!technician.getEmail().matches("^(.+)@(.+)$")) {
            throw new InvalidEmailException();
        }
        if (technician.getDate() < Utils.Date_today)
        {
            throw new InvalidDateException();
        }
        technicianRepo.save(technician);
    }

    public Optional<Technician> signIn(String email , String pass){
        if (technicianRepo.findClientByEmail(email) == null || !technicianRepo.findClientByEmailAndPass(email, pass).isPresent()){
            throw new InvalidEntityException();
        }else
            return technicianRepo.findClientByEmailAndPass(email , pass);
    }

    public Technician findByEmail(String email){
        return technicianRepo.findClientByEmail(email);
    }

    public void create(Technician technician){
        technicianRepo.save(technician);
    }

    public void changePass(String email , String pass , String passNew){
        Optional<Technician> te = signIn(email , pass);
        if (passNew.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")){
            te.get().setPass(passNew);
            technicianRepo.save(te.get());
        }else {
            throw new InvalidPassException();
        }
    }

    public void updateTechnician(Technician technician){
        technicianRepo.save(technician);
    }

    public void delete(Technician technician){
        technicianRepo.delete(technician);
    }
}
