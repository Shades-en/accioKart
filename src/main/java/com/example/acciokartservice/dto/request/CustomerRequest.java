package com.example.acciokartservice.dto.request;

import com.example.acciokartservice.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerRequest {

    String name;
    String email;
    int age;
    Gender gender;
    String city;
}
