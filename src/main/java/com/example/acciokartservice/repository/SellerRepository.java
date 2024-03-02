package com.example.acciokartservice.repository;

import com.example.acciokartservice.model.Identity;
import com.example.acciokartservice.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Optional<Seller> findByUniqueSellerNumber(String uniqueSellerNumber);
}
