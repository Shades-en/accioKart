package com.example.acciokartservice.dto.response;

import com.example.acciokartservice.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    String productName;
    double price;
    int quantityAvailable;
    ProductStatus productStatus;
    String description;
    SellerResponse sellerResponse;
}
