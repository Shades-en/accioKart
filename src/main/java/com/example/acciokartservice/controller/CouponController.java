package com.example.acciokartservice.controller;

import com.example.acciokartservice.service.CouponService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
@AllArgsConstructor
@Slf4j
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public ResponseEntity<String> addCoupon(@RequestParam("code") String couponCode,
                                    @RequestParam("discount") double percentageDiscount){
        couponService.addCoupon(couponCode, percentageDiscount);
        return new ResponseEntity<>("Coupon added successfully", HttpStatus.CREATED);

    }

    @PutMapping("/assign")
    public ResponseEntity<String> assignCoupon(@RequestParam("email") String email,
                                               @RequestParam("code") String couponCode){
        String message = couponService.assignCoupon(email, couponCode);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }
}
