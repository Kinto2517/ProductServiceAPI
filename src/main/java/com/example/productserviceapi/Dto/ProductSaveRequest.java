package com.example.productserviceapi.Dto;

import com.example.productserviceapi.Entity.User;

import java.math.BigDecimal;

public record ProductSaveRequest(String name, String code, String description, String brand, String currency, BigDecimal price, int stockStatus, boolean deleted) {
}
