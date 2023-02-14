package com.example.achar.service;

import com.example.achar.exception.*;
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

    public void createClient(Client client){
        if (clientRepo.findClientByEmail(client.getEmail()) != null){
            throw new DuplicateUserException();
        }

        if (!client.getPass().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
            throw new InvalidPassException();
        }
        if (!client.getEmail().matches("^(.+)@(.+)$")) {
            throw new InvalidEmailException();
        }
        if (client.getDate() < Utils.Date_today)
        {
            throw new InvalidDateException();
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

    public void changePass(String email , String pass , String passNew){
        Optional<Client> client = signIn(email , pass);
        if (passNew.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")){
            client.get().setPass(passNew);
            clientRepo.save(client.get());
        }else {
            throw new InvalidPassException();
        }
    }


}
