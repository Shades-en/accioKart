package com.example.acciokartservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SellerResponse {
    String Name;
    String uniqueSellerNumber;
    String mobileNum;
    int age;
}
