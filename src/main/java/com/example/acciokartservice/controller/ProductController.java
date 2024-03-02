package com.example.acciokartservice.controller;

import com.example.acciokartservice.dto.request.ProductRequest;
import com.example.acciokartservice.dto.response.ProductResponse;
import com.example.acciokartservice.model.Product;
import com.example.acciokartservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ProductResponse addProduct(@RequestParam("uniqueSellerNumber") String uniqueSellerNumber,
                                      @RequestBody ProductRequest productRequest){
        return productService.addProduct(productRequest, uniqueSellerNumber);
    }
}
