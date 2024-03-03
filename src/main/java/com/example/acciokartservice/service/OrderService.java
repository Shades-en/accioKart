package com.example.acciokartservice.service;

import com.example.acciokartservice.Enum.ProductStatus;
import com.example.acciokartservice.dto.response.OrderResponse;
import com.example.acciokartservice.exception.CustomerNotFoundException;
import com.example.acciokartservice.exception.ProductNotFoundException;
import com.example.acciokartservice.model.Coupon;
import com.example.acciokartservice.model.Customer;
import com.example.acciokartservice.model.OrderEntity;
import com.example.acciokartservice.model.Product;
import com.example.acciokartservice.repository.CouponRepository;
import com.example.acciokartservice.repository.CustomerRepository;
import com.example.acciokartservice.repository.ProductRepository;
import com.example.acciokartservice.service.transformer.OrderTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerRepository customerRepository;
    private  final ProductRepository productRepository;
    private final CouponRepository couponRepository;

    public OrderResponse placeOrder(Map<String, Object> paramsMap) {
        String email = (String) paramsMap.get("customer-email");
        Integer quantityRequired = Integer.parseInt((String)  paramsMap.get("quantity"));
        Integer productId = Integer.parseInt((String) paramsMap.get("product-id"));

        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if(customerOptional.isEmpty()){
            throw new CustomerNotFoundException("Invalid Email Id");
        }

        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Invalid product Id");
        }

        Product product = productOptional.get();
        if(product.getQuantityAvailable()<quantityRequired){
            throw new ProductNotFoundException("Insufficient Quantity");
        }

        Customer customer = customerOptional.get();
        double totalValue = quantityRequired*product.getPrice();
        totalValue = applyDiscount(customer, totalValue);
        OrderEntity orderEntity = OrderEntity.builder()
                .orderId(String.valueOf(UUID.randomUUID()))
                .numberOfItems(quantityRequired)
                .totalValue(totalValue)
                .build();

        customer.getOrders().add(orderEntity);
        product.setQuantityAvailable(product.getQuantityAvailable() - quantityRequired);
        if(product.getQuantityAvailable()==0){
            product.setProductStatus(ProductStatus.OUTOFSTOCK);
        }

        Customer savedCustomer = customerRepository.save(customer);
        productRepository.save(product);

        OrderEntity latestOrder = savedCustomer.getOrders().get(customer.getOrders().size()-1);

        return OrderTransformer.prepareOrderResponse(latestOrder);
    }

    private double applyDiscount(Customer customer, double totalValue){
        Optional<Coupon> couponOptional = couponRepository.getRandomCoupon(customer);
        if(couponOptional.isPresent()){
            Coupon coupon = couponOptional.get();
            totalValue -= (totalValue*coupon.getPercentageDiscount())/100.0;
        }
        return totalValue;

    }
}


