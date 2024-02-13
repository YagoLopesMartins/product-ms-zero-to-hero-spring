package com.example.productmsprojectspringzerotohero.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class WarehouseDTO {

    private Long productId;
    private int quantity;
}
