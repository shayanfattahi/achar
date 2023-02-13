package com.example.achar.service;

import com.example.achar.exception.DuplicateUserException;
import com.example.achar.exception.InvalidEntityException;
import com.example.achar.exception.InvalidException;
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

    public void createTechnician(Technician technician) throws InvalidException {
        if (technicianRepo.findClientByEmail(technician.getEmail()) != null){
            throw new DuplicateUserException();
        }

        if (!technician.getPass().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
            throw new InvalidException("format pass is wrong!!!");
        }
        if (!technician.getEmail().matches("^(.+)@(.+)$")) {
            throw new InvalidException("format email is wrong!!!");
        }
        if (technician.getDate() < Utils.Date_today)
        {
            throw new InvalidException("tarikh vase roozhaie gozashte ast");
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
}
