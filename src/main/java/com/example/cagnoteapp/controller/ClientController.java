package com.example.cagnoteapp.controller;

import com.example.cagnoteapp.model.Client;
import com.example.cagnoteapp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client newClient = clientService.addNewClient(client.getFirstName(), client.getLastName(), client.getUserName());
        return ResponseEntity.ok(newClient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client.get());
    }
}