package com.example.achar.repository;

import com.example.achar.model.users.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnicianRepo extends JpaRepository<Technician , Long> {

    Technician findClientByEmail(String email);

    Optional<Technician> findClientByEmailAndPass(String email , String pass);
}
