package com.example.productmsprojectspringzerotohero.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private String name;
    private String description;
    private Double price;
    private Boolean available;
}
