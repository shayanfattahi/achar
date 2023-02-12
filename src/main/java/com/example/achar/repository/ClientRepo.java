package com.example.achar.repository;

import com.example.achar.model.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client , Long> {

    Client findClientByEmail(String email);

    Optional<Client> findClientByEmailAndPass(String email , String pass);


}
