package com.example.acciokartservice.service;

import com.example.acciokartservice.Enum.Gender;
import com.example.acciokartservice.exception.CustomerNotFoundException;
import com.example.acciokartservice.model.Customer;
import com.example.acciokartservice.model.Identity;
import com.example.acciokartservice.repository.CustomerRepository;
import com.example.acciokartservice.repository.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdentityService {

    @Autowired
    IdentityRepository identityRepository;

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    @Autowired
    public IdentityService(CustomerService customerService, CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    public Customer addIdentity(Identity identity, String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Customer Email");
        }

        Customer customer = optionalCustomer.get();
        customer.setIdentity(identity);
        customerRepository.save(customer);
        return customer;
    }
}
