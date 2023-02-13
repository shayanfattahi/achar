package com.example.achar.service;

import com.example.achar.exception.DuplicateUserException;
import com.example.achar.exception.InvalidEntityException;
import com.example.achar.exception.InvalidException;
import com.example.achar.model.users.Client;
import com.example.achar.repository.ClientRepo;
import com.example.achar.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepo clientRepo;
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public void createClient(Client client) throws InvalidException {
        if (clientRepo.findClientByEmail(client.getEmail()) != null){
            throw new DuplicateUserException();
        }

        if (!client.getPass().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
            throw new InvalidException("format pass is wrong!!!");
        }
        if (!client.getEmail().matches("^(.+)@(.+)$")) {
            throw new InvalidException("format email is wrong!!!");
        }
        if (client.getDate() < Utils.Date_today)
        {
            throw new InvalidException("tarikh vase roozhaie gozashte ast");
        }
        clientRepo.save(client);
    }

    public Optional<Client> signIn(String email , String pass){
        if (clientRepo.findClientByEmail(email) == null || !clientRepo.findClientByEmailAndPass(email, pass).isPresent()){
            throw new InvalidEntityException();
        }else
            return clientRepo.findClientByEmailAndPass(email , pass);
    }

    public Client findByEmail(String email){
        return clientRepo.findClientByEmail(email);
    }
}
