package com.example.achar.controller;

import com.example.achar.exception.InvalidException;
import com.example.achar.model.users.Client;
import com.example.achar.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/Client")
public class ClientController{
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public String register(@RequestBody Client client) throws InvalidException {
        clientService.createClient(client);
        return "ok";
    }
}
