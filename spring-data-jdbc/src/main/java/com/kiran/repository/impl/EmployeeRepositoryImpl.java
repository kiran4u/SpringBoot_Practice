package com.kiran.repository.impl;

import com.kiran.entity.Employee;
import com.kiran.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update("INSERT INTO EMPLOYEES_DATA (name, dept,email,doj) values(?,?,?,?)",
                employee.getName(), employee.getDept(), employee.getEmail(), employee.getDoj());
    }


    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM EMPLOYEES_DATA", new EmployeeRowMapper());
    }

    @Override
    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("SELECT * FROM EMPLOYEES_DATA", new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public Employee findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM EMPLOYEES_DATA WHERE id =? ",
                new BeanPropertyRowMapper<>(Employee.class),
                id);
    }

    @Override
    public String getNameById(int id) {
        return jdbcTemplate.queryForObject("SELECT name FROM EMPLOYEES_DATA WHERE id = ?",
                String.class,
                id);
    }

    @Override
    public List<Employee> findByNameAndDept(String name, String dept) {
        return jdbcTemplate.query("SELECT * FROM EMPLOYEES_DATA WHERE name =? and dept = ? ",
                new BeanPropertyRowMapper<>(Employee.class), name,dept);
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update("UPDATE EMPLOYEES_DATA SET name =?, dept =?, email =?, doj =? WHERE id =?",
                employee.getName(),
                employee.getDept(), employee.getEmail(), employee.getDoj());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROm EMPLOYEES_DATA WHERE id =?", id);
    }
}
