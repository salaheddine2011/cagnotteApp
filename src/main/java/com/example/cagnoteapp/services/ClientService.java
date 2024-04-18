package com.example.cagnoteapp.services;

import com.example.cagnoteapp.model.Client;
import com.example.cagnoteapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Client addNewClient(String firstName, String lastName, String userName) {
        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setUserName(userName);
        return clientRepository.save(client);
    }

    public Client getClientByUserName(String userName){
       return  clientRepository.findByUserName(userName);
    }
    public Optional<Client> getClientById(Long id){
        return  clientRepository.findById(id);
    }
}
