package com.example.cagnoteapp;

import com.example.cagnoteapp.exception.ClientNotFoundException;
import com.example.cagnoteapp.model.Client;
import com.example.cagnoteapp.model.Transaction;
import com.example.cagnoteapp.repository.ClientRepository;
import com.example.cagnoteapp.repository.TransactionRepository;
import com.example.cagnoteapp.services.CagnotteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CagnotteServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private CagnotteService cagnotteService;

    private Client client;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setId(1L);
        transaction = new Transaction();
        transaction.setClient(client);
        transaction.setAmount(new BigDecimal("50"));
        transaction.setTimestamp(LocalDateTime.now());
    }

    @Test
    void addAmountToCagnotte_ClientExists_AddsAmount() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        cagnotteService.addAmountToCagnotte(1L, new BigDecimal("50"));
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void addAmountToCagnotte_ClientNotFound_ThrowsException() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ClientNotFoundException.class, () -> {
            cagnotteService.addAmountToCagnotte(2L, new BigDecimal("50"));
        });
        assertTrue(exception.getMessage().contains("Client with ID 2 not found"));
    }

    @Test
    void isCagnotteAvailable_ConditionsMet_ReturnsTrue() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(transactionRepository.sumAmountByClientId(1L)).thenReturn(new BigDecimal("30"));
        when(transactionRepository.findByClientId(1L)).thenReturn(java.util.Arrays.asList(new Transaction(), new Transaction(), new Transaction()));
        assertTrue(cagnotteService.isCagnotteAvailable(1L));
    }

    @Test
    void isCagnotteAvailable_ConditionsNotMet_ReturnsFalse() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(transactionRepository.sumAmountByClientId(1L)).thenReturn(new BigDecimal("5"));
        when(transactionRepository.findByClientId(1L)).thenReturn(java.util.Arrays.asList(new Transaction(), new Transaction()));
        assertFalse(cagnotteService.isCagnotteAvailable(1L));
    }
}