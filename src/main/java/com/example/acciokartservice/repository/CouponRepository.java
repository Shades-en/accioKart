package com.example.acciokartservice.repository;

import com.example.acciokartservice.model.Coupon;
import com.example.acciokartservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    Optional<Coupon> findByCouponCode(String email);

    @Query("select c from Coupon c where :customer member of c.customers order by rand() limit 1")
    Optional<Coupon> getRandomCoupon(@Param("customer") Customer customer);
}
