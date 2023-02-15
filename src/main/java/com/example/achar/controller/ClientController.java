package com.example.achar.controller;

import com.example.achar.dto.client.ClientDto;
import com.example.achar.dto.client.ClientMapper;
import com.example.achar.dto.client.GetClientDto;
import com.example.achar.model.users.Client;
import com.example.achar.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ClientDto register(@RequestBody ClientDto clientDto){
        clientService.createClient(dtoToModelWithMapStruct(clientDto));
        return clientDto;
    }

    @PostMapping("/logIn")
    public GetClientDto logIn(@RequestBody Client client){
        clientService.signIn(client.getEmail(), client.getPass());
        return modelToGetDto(client);
    }

    @PutMapping("/changePass/{email}/{pass}/{passNew}")
    public void changePass(@PathVariable String email , @PathVariable String pass , @PathVariable String passNew ){
        clientService.changePass(email , pass , passNew);
    }
}
