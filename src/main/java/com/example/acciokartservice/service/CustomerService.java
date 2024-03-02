package com.example.acciokartservice.service;

import com.example.acciokartservice.Enum.Gender;
import com.example.acciokartservice.dto.request.CustomerRequest;
import com.example.acciokartservice.dto.response.CustomerResponse;
import com.example.acciokartservice.exception.CustomerNotFoundException;
import com.example.acciokartservice.model.Customer;
import com.example.acciokartservice.repository.CustomerRepository;
import com.example.acciokartservice.service.transformer.CustomerTransformer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Slf4j
public class CustomerService {
//    Logger logger = (Logger) LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        Customer customer = CustomerTransformer.customerRequestToCustomer(customerRequest);
        customerRepository.save(customer);
        return CustomerTransformer.customerToCustomerResponse(customer);
    }

    public CustomerResponse getCustomer(int id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Customer ID");
        }

        Customer customer = optionalCustomer.get();

        log.debug("Passed Validation Step");
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setName(customer.getName());
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setCreatedAt(customer.getCreatedAt());


        return customerResponse;
    }

    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for(Customer customer: customers){
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return customerResponses;
    }

    public CustomerResponse getCustomerByEmail(String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Customer Email");
        }

        Customer customer = optionalCustomer.get();
        return CustomerTransformer.customerToCustomerResponse(customer);
    }

    public List<CustomerResponse> getAllByGenderAndAge(Gender gender, int age) {
        List<Customer> customers = customerRepository.findByGenderAndAge(gender, age);
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for(Customer customer: customers){
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return customerResponses;
    }


    public int getCountOfAgeGreaterThan(int age) {
        return customerRepository.getCountOfAgeGreaterThan(age);
    }

    public int getCountGender(Gender gender) {
        return customerRepository.getCountGender(gender);
    }

    public void deleteCustomer(String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Customer not found");
        }
        customerRepository.delete(optionalCustomer.get());
    }
}
