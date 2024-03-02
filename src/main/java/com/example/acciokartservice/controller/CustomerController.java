package com.example.acciokartservice.controller;

import com.example.acciokartservice.Enum.Gender;
import com.example.acciokartservice.dto.request.CustomerRequest;
import com.example.acciokartservice.dto.response.CustomerResponse;
import com.example.acciokartservice.model.Customer;
import com.example.acciokartservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public CustomerResponse addCustomer(@RequestBody CustomerRequest customerRequest){
        return customerService.addCustomer(customerRequest);
    }

    @GetMapping("/get/{id}")
    public CustomerResponse getCustomer(@PathVariable("id") int id){
        return customerService.getCustomer(id);
    }

    @GetMapping("/getall")
    public List<CustomerResponse> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/get/email/{email}")
    public CustomerResponse getCustomerByEmail(@PathVariable("email") String email){
        return customerService.getCustomerByEmail(email);
    }

    @GetMapping("/get-by-gender-age")
    public List<CustomerResponse> getAllByGenderAndAge(@RequestParam("gender") Gender gender,
                                                        @RequestParam("age") int age){
        return customerService.getAllByGenderAndAge(gender, age);
    }

    @GetMapping("/get-count/{age}")
    public int getCountOfAgeGreaterThan(@PathVariable int age){
        return customerService.getCountOfAgeGreaterThan(age);
    }

    @GetMapping("/get-count-gender")
    public int getCountGender(@RequestParam Gender gender){
        return customerService.getCountGender(gender);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestParam("email") String email){
        customerService.deleteCustomer(email);
        return new ResponseEntity<>("Customer Deleted", HttpStatus.ACCEPTED);
    }
}
