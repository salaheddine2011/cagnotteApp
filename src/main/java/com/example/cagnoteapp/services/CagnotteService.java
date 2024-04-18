package com.example.cagnoteapp.services;

import com.example.cagnoteapp.exception.ClientNotFoundException;
import com.example.cagnoteapp.model.Transaction;
import com.example.cagnoteapp.model.Client;
import com.example.cagnoteapp.repository.TransactionRepository;
import com.example.cagnoteapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CagnotteService {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public void addAmountToCagnotte(Long clientId, BigDecimal amount) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client == null) {
            throw new IllegalArgumentException("Client not found");
        }
        Transaction transaction = new Transaction();
        transaction.setClient(client.get());
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    public boolean isCagnotteAvailable(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client with ID " + clientId + " not found."));
        if (client == null) {
            throw new IllegalArgumentException("Client not found");
        }
        BigDecimal total = transactionRepository.sumAmountByClientId(client.getId());
        int count = transactionRepository.findByClientId(client.getId()).size();
        return count >= 3 && (total != null && total.compareTo(new BigDecimal("10")) >= 0);
    }



}
