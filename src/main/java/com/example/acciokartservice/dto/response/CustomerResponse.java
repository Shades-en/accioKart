package com.example.acciokartservice.dto.response;

import com.example.acciokartservice.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponse {
    String name;
    String email;
    Date createdAt;
}
