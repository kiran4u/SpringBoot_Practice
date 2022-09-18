package com.kiran.controller;

import com.kiran.dto.Customer;
import com.kiran.dto.CustomerDto;
import com.kiran.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping
    public List<Customer>  saveCustomers(@RequestBody List<Customer> customers) {
        return customerService.addNewCustomers(customers);

    }

    @GetMapping
    public List<CustomerDto> fetchAllCustomers() {
        return customerService.getCustomers();
    }


}
