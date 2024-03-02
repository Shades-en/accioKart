package com.example.acciokartservice.service.transformer;

import com.example.acciokartservice.dto.request.ProductRequest;
import com.example.acciokartservice.dto.response.ProductResponse;
import com.example.acciokartservice.dto.response.SellerResponse;
import com.example.acciokartservice.model.Product;
import com.example.acciokartservice.model.Seller;

public class ProductTransformer {
    public static Product productRequestToProduct(ProductRequest productRequest){
        return Product.builder()
                .productName(productRequest.getProductName())
                .productStatus(productRequest.getProductStatus())
                .description(productRequest.getDescription())
                .quantityAvailable(productRequest.getQuantityAvailable())
                .price(productRequest.getPrice())
                .build();
    }

    public static ProductResponse productToProductResponse(Product product){
        SellerResponse sellerResponse = SellerTransformer.sellerToSellerResponse(product.getSeller());
        return ProductResponse.builder()
                .productName(product.getProductName())
                .description(product.getDescription())
                .quantityAvailable(product.getQuantityAvailable())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .sellerResponse(sellerResponse)
                .build();
    }
}
