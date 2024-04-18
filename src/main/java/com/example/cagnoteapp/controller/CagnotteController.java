package com.example.cagnoteapp.controller;

import com.example.cagnoteapp.dto.AmountDTO;
import com.example.cagnoteapp.exception.ClientNotFoundException;
import com.example.cagnoteapp.model.Client;
import com.example.cagnoteapp.services.CagnotteService;
import com.example.cagnoteapp.dto.CagnotteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/cagnottes")
public class CagnotteController {

    @Autowired
    private CagnotteService cagnotteService;

    @PostMapping("/{clientId}")
    public ResponseEntity<?> addAmountToCagnotte(@PathVariable Long clientId, @RequestBody AmountDTO amountDTO) {
        try {
            cagnotteService.addAmountToCagnotte(clientId, amountDTO.getAmount());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<?> isCagnotteAvailable(@PathVariable Long clientId) {
        try {
            boolean available = cagnotteService.isCagnotteAvailable(clientId);
            return ResponseEntity.ok(available);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}