package com.example.acciokartservice.service;

import com.example.acciokartservice.dto.request.ProductRequest;
import com.example.acciokartservice.dto.response.ProductResponse;
import com.example.acciokartservice.exception.SellerNotFoundException;
import com.example.acciokartservice.model.Product;
import com.example.acciokartservice.model.Seller;
import com.example.acciokartservice.repository.SellerRepository;
import com.example.acciokartservice.service.transformer.ProductTransformer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final SellerRepository sellerRepository;
    public ProductService(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }
    public ProductResponse addProduct(ProductRequest productRequest, String uniqueSellerNumber) {
        Optional<Seller> optionalSeller = sellerRepository.findByUniqueSellerNumber(uniqueSellerNumber);
        if(optionalSeller.isEmpty()){
            throw new SellerNotFoundException("Invalid Seller Number");
        }
        Seller seller = optionalSeller.get();
        Product product = ProductTransformer.productRequestToProduct(productRequest);

        product.setSeller(seller);
        seller.getProducts().add(product);

        Seller savedSeller = sellerRepository.save(seller);
        Product savedProduct = savedSeller.getProducts().get(savedSeller.getProducts().size()-1);
        return ProductTransformer.productToProductResponse(savedProduct);
    }
}
