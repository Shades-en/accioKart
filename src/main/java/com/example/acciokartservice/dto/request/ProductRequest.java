package com.example.acciokartservice.dto.request;

import com.example.acciokartservice.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {
    String productName;
    double price;
    int quantityAvailable;
    ProductStatus productStatus;
    String description;
}
