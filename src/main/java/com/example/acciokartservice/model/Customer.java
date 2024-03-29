package com.example.acciokartservice.model;

import com.example.acciokartservice.Enum.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//if you change table name - creates a new table
//add a field, it adds a field
//remove a field, doesnt remove a field
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
//@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Column(unique = true)
    @Email
    String email;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Size(min=2, max=20)
    String city;

    @CreationTimestamp
    Date createdAt;

    int age;

    @OneToOne(cascade = CascadeType.ALL) // first part is current class //if you delete customer, identity gets deleted too
    @JoinColumn(name="identity_id")
    Identity identity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    List<OrderEntity> orders = new ArrayList<>();
}
