package com.example.productmsprojectspringzerotohero.repository;

import com.example.productmsprojectspringzerotohero.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
