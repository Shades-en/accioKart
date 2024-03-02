package com.example.acciokartservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true)
    String couponCode;

    double percentageDiscount;

    @ManyToMany
    @JoinTable(name="customers_coupons", joinColumns = {@JoinColumn(name="coupon_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name="customer_id", referencedColumnName = "id")}) //ref column has to have attribute name, you can also omit ref column
    List<Customer> customers = new ArrayList<>();
}
