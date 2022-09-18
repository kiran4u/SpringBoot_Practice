package com.kiran.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String DOB; //dd/MM/yyyy

}
