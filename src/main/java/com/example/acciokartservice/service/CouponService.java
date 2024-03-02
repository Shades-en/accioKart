package com.example.acciokartservice.service;

import com.example.acciokartservice.exception.CouponNotFoundException;
import com.example.acciokartservice.exception.CustomerNotFoundException;
import com.example.acciokartservice.model.Coupon;
import com.example.acciokartservice.model.Customer;
import com.example.acciokartservice.repository.CouponRepository;
import com.example.acciokartservice.repository.CustomerRepository;
import com.example.acciokartservice.utils.CustomerValidator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
//@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidator;

    public String addCoupon(String couponCode, double percentageDiscount){
        Coupon coupon = Coupon.builder()
                .couponCode(couponCode)
                .percentageDiscount(percentageDiscount)
                .build();
        couponRepository.save(coupon);
        return "DONE";
    }

    public String assignCoupon(String email, String couponCode) {
        if(!customerValidator.isValid(email)){
            throw new CustomerNotFoundException("Invalid Customer Email");
        }

        Customer customer = customerRepository.findByEmail(email).get();

        Optional<Coupon> optionalCoupon = couponRepository.findByCouponCode(couponCode);
        if(optionalCoupon.isEmpty()){
            throw new CouponNotFoundException("Invalid Coupon code");
        }

        Coupon coupon = optionalCoupon.get();
        coupon.getCustomers().add(customer);
        couponRepository.save(coupon);
        return "Coupon Assigned";
    }
}
