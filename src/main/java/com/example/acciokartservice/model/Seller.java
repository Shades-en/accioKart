package com.example.acciokartservice.model;

import com.example.acciokartservice.Enum.Gender;
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
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true)
    String uniqueSellerNumber;

    String name;

    @Column(unique = true)
    String mobileNum;

    int age;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<>();
}

//many - product, one - seller
//bidirectional relationship

//many side - single attribute ref attr
//one side - list attribute ref attr

//many side - manyToOne annotation
//one side - oneToMany annotation

//many side - join column
//one side - mappedBy

//mappedby side is parent side