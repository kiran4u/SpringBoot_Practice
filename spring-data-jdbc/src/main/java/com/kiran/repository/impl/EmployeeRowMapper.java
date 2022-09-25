package com.kiran.repository.impl;

import com.kiran.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNumber) throws SQLException {
        return Employee.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .dept(rs.getString("dept"))
                .email(rs.getString("email"))
                .doj(rs.getDate("doj"))
                .build();

    }

}
