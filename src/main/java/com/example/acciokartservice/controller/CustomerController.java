package com.example.acciokartservice.controller;

import com.example.acciokartservice.Enum.Gender;
import com.example.acciokartservice.model.Customer;
import com.example.acciokartservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private  final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping("/get/{id}")
    public Customer getCustomer(@PathVariable("id") int id){
        return customerService.getCustomer(id);
    }

    @GetMapping("/getall")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/get/email/{email}")
    public Customer getCustomerByEmail(@PathVariable("email") String email){
        return customerService.getCustomerByEmail(email);
    }

    @GetMapping("/get-by-gender-age")
    public List<Customer> getAllByGenderAndAge(@RequestParam("gender") Gender gender,
                                               @RequestParam("age") int age){
        return customerService.getAllByGenderAndAge(gender, age);
    }
}
