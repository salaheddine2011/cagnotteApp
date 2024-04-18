package com.example.cagnoteapp.dto;

import java.math.BigDecimal;

public class CagnotteResponse {
    private BigDecimal total;
    private boolean isAvailable;

    public CagnotteResponse(BigDecimal total, boolean isAvailable) {
        this.total = total;
        this.isAvailable = isAvailable;
    }

    // Getters
    public BigDecimal getTotal() {
        return total;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
