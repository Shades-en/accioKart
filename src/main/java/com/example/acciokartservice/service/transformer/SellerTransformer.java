package com.example.acciokartservice.service.transformer;

import com.example.acciokartservice.dto.request.SellerRequest;
import com.example.acciokartservice.dto.response.SellerResponse;
import com.example.acciokartservice.model.Seller;

import java.util.UUID;

public class SellerTransformer {
    public static Seller sellerRequestToSeller(SellerRequest sellerRequest){
        return Seller.builder()
                .uniqueSellerNumber(String.valueOf(UUID.randomUUID()))
                .name(sellerRequest.getName())
                .mobileNum(sellerRequest.getMobileNum())
                .age(sellerRequest.getAge())
                .build();
    }

    public static SellerResponse sellerToSellerResponse(Seller seller){
        return SellerResponse.builder()
                .uniqueSellerNumber(seller.getUniqueSellerNumber())
                .mobileNum(seller.getMobileNum())
                .Name(seller.getName())
                .age(seller.getAge())
                .build();
    }
}
