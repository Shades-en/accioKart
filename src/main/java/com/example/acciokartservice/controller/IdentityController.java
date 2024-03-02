package com.example.acciokartservice.controller;

import com.example.acciokartservice.Enum.Gender;
import com.example.acciokartservice.model.Customer;
import com.example.acciokartservice.model.Identity;
import com.example.acciokartservice.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/identity")
public class IdentityController {

    private final IdentityService identityService;

    @Autowired
    public  IdentityController(IdentityService identityService){
        this.identityService = identityService;
    }

    @PostMapping("/add")
    public Customer addIdentity(@RequestBody Identity identity,
                                @RequestParam("email") String email){
        return identityService.addIdentity(identity, email);
    }
}
