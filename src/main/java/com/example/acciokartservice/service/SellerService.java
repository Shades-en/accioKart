package com.example.acciokartservice.service;

import com.example.acciokartservice.dto.request.SellerRequest;
import com.example.acciokartservice.dto.response.SellerResponse;
import com.example.acciokartservice.model.Seller;
import com.example.acciokartservice.repository.SellerRepository;
import com.example.acciokartservice.service.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerResponse add(SellerRequest sellerRequest) {
        Seller seller = SellerTransformer.sellerRequestToSeller(sellerRequest);
        Seller resp = sellerRepository.save(seller);
        return SellerTransformer.sellerToSellerResponse(resp);
    }
}
