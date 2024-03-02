package com.example.acciokartservice.repository;

import com.example.acciokartservice.Enum.Gender;
import com.example.acciokartservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email); //object attribute not sql table attribute

    List<Customer> findByGenderAndAge(Gender gender, int age);

    @Query("select count(c) from Customer c where c.age > :age")
    int getCountOfAgeGreaterThan(@Param("age") int age);

    @Query("select count(c) from Customer c where gender = :gender")
    int getCountGender(@Param("gender") Gender gender);
}