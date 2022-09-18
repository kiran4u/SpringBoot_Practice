package com.kiran.service;

import com.kiran.dto.Customer;
import com.kiran.dto.CustomerDto;
import com.kiran.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile(value = {"dev", "stg", "prod"})
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    @Value("${application.message}")
    private String message;

    @PostConstruct
    public void init() {
        System.out.println("******************"+ message);
    }

    @PostConstruct
    @ConditionalOnProperty(value = "spring.profiles.active", havingValue = "prod",  matchIfMissing = false)
    public void init2() {
        System.out.println("******************"+ message + " **************************");
    }

    public List<Customer> addNewCustomers(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    public List<CustomerDto> getCustomers(){
        List<Customer>  customerList = customerRepository.findAll();

       return customerList.stream().map(customer -> new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(),
                customer.getPhone(), getDateFormat(customer.getDOB())))
                .collect(Collectors.toList());
    }


    private String getDateFormat(Date date) {
        return new SimpleDateFormat("MM/dd/yyyy").format(date);
    }






}
