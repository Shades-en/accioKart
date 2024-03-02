package com.example.acciokartservice.repository;

import com.example.acciokartservice.model.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface IdentityRepository extends JpaRepository<Identity, Integer>{
}
