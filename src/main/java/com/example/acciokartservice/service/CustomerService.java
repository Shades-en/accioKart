package com.example.acciokartservice.service;

import com.example.acciokartservice.Enum.Gender;
import com.example.acciokartservice.exception.CustomerNotFoundException;
import com.example.acciokartservice.model.Customer;
import com.example.acciokartservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(int id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Customer ID");
        }

        Customer customer = optionalCustomer.get();
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByEmail(String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Customer Email");
        }

        Customer customer = optionalCustomer.get();
        return customer;
    }

    public List<Customer> getAllByGenderAndAge(Gender gender, int age) {
        return customerRepository.findByGenderAndAge(gender, age);
    }


}
