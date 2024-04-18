package com.example.cagnoteapp;

import com.example.cagnoteapp.model.Client;
import com.example.cagnoteapp.model.Transaction;
import com.example.cagnoteapp.repository.ClientRepository;
import com.example.cagnoteapp.repository.TransactionRepository;
import com.example.cagnoteapp.services.CagnotteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CagnotteServiceTests {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private CagnotteService cagnotteService;

    @Test
    public void testAddAmountToCagnotte() {
        Client client = new Client();
        client.setId(1L);
        when(clientRepository.findById(anyLong())).thenReturn(java.util.Optional.of(client));

        cagnotteService.addAmountToCagnotte(1L, new BigDecimal("50"));

        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    public void testCagnotteAvailability() {
        when(transactionRepository.sumAmountByClientId(anyLong())).thenReturn(new BigDecimal("50"));
        when(transactionRepository.findByClientId(anyLong())).thenReturn(Arrays.asList(new Transaction(), new Transaction(), new Transaction()));

        assertTrue(cagnotteService.isCagnotteAvailable(1L));
    }

    @Test
    public void testCagnotteNotAvailableDueToInsufficientFunds() {
        when(transactionRepository.sumAmountByClientId(anyLong())).thenReturn(new BigDecimal("5"));
        when(transactionRepository.findByClientId(anyLong())).thenReturn(Arrays.asList(new Transaction(), new Transaction(), new Transaction()));

        assertFalse(cagnotteService.isCagnotteAvailable(1L));
    }

    @Test
    public void testClientNotFoundException() {
        when(clientRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        assertThrows(RuntimeException.class, () -> cagnotteService.addAmountToCagnotte(1L, new BigDecimal("20")));
    }
}
