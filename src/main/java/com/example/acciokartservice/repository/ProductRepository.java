package com.example.acciokartservice.repository;

import com.example.acciokartservice.model.Product;
import com.example.acciokartservice.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
