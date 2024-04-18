package com.example.cagnoteapp.repository;

import com.example.cagnoteapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findById(Long clientID);

    Client findByUserName(String userName);

}
