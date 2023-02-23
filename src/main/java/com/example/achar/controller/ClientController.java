package com.example.achar.controller;

import com.example.achar.dto.client.ClientDto;
import com.example.achar.dto.client.ClientMapper;
import com.example.achar.dto.client.GetClientDto;
import com.example.achar.model.users.Client;
import com.example.achar.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Controller
@RequestMapping("/Client")
public class ClientController{
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    private Client dtoToModelWithMapStruct(ClientDto clientDto) {
        return ClientMapper.INSTANCE.dtoToModel(clientDto);
    }

    private GetClientDto modelToGetDto(Client client){
        return ClientMapper.INSTANCE.modelToGetDto(client);
    }

    @PostMapping("/register")
    public ClientDto register(@Valid @RequestBody ClientDto clientDto){
        clientService.createClient(dtoToModelWithMapStruct(clientDto));
        return clientDto;
    }

    @PostMapping("/logIn/{email}/{pass}")
    public GetClientDto logIn(@PathVariable String email , @PathVariable String pass){
        Optional<Client> client = clientService.signIn(email, pass);
        return modelToGetDto(client.get());
    }

    @PutMapping("/changePass/{email}/{pass}/{passNew}")
    public String changePass(@PathVariable String email , @PathVariable String pass , @PathVariable String passNew ){
        clientService.changePass(email , pass , passNew);
        return "ok";
    }
}
