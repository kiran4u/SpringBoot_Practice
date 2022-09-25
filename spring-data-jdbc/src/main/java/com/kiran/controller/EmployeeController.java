package com.kiran.controller;

import com.kiran.entity.Employee;
import com.kiran.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    public EmployeeService service;

    @PostMapping
    public String saveEmployee(@RequestBody Employee employee) {
       return service.saveEmployee(employee);
    }

    @GetMapping("/2nd")
    public List<Employee> getEmployeesBy2ndApproach(){
        return service.getEmployeesUsingBeanPropertyRowMapper();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeByid(@PathVariable int id){
        return service.getEmployeeByid(id);
    }

    @GetMapping("/{name}/{dept}")
    public List<Employee> findEmployeesByNameAndDept(@PathVariable String name, @PathVariable String dept){
        return service.findEmployeesByNameAndDept(name, dept);

    }

    @PutMapping
    public String update(@RequestBody Employee employee){
       return service.update(employee);
    }

    @DeleteMapping("/")
    public String deleteEmployee(@PathVariable int id){
        return service.deleteEmployee(id);
    }

}

