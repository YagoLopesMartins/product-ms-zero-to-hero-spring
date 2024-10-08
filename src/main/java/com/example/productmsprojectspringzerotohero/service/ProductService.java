package com.example.productmsprojectspringzerotohero.service;

import com.example.productmsprojectspringzerotohero.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    Optional<ProductDTO> create (ProductDTO request);

    List<ProductDTO> getAll();

    Optional<ProductDTO> getById(Long id);

    boolean inactive(Long id);

    Optional<ProductDTO> update(Long id, ProductDTO request);
}
