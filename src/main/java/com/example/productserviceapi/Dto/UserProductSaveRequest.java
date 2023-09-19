package com.example.productserviceapi.Dto;

import java.math.BigDecimal;

public record UserProductSaveRequest(Long userId, String name, String code, String description, String brand, String currency, BigDecimal price, int stockStatus, boolean deleted) {
}
