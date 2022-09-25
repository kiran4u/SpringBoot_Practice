package com.kiran.service;

import com.kiran.entity.Employee;
import com.kiran.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    public EmployeeRepository repository;

    public String saveEmployee(Employee employee) {
        int count = repository.save(employee);
        return "RECORD INSERTED " + count ;
    }

    public List<Employee> getEmployeesUsingBeanPropertyRowMapper(){
        return repository.getAllEmployees();
    }

    public Employee getEmployeeByid(int id){
        return repository.findById(id);
    }

    public String getNameById(int id){
        return repository.getNameById(id);
    }

    public List<Employee> findEmployeesByNameAndDept(String name, String dept){
        return repository.findByNameAndDept(name, dept);
    }

    public String update(Employee employee){
        int count = repository.update(employee);
        return count + " RECORD UPDATED !";
    }

    public String deleteEmployee(int id){
        int count = repository.delete(id);
        return count + " RECORD DELETED !";
    }
}
