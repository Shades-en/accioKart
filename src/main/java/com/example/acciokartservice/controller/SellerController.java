package com.example.acciokartservice.controller;

import com.example.acciokartservice.dto.request.SellerRequest;
import com.example.acciokartservice.dto.response.SellerResponse;
import com.example.acciokartservice.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    private final SellerService sellerService;
    @Autowired
    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }
    @PostMapping
    public SellerResponse addSeller(@RequestBody SellerRequest sellerRequest){
        return sellerService.add(sellerRequest);
    }
}
