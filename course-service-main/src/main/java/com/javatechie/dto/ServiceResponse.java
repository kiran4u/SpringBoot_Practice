package com.javatechie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse<T> {

    private T response;

    private HttpStatus status;

    private List<ErrorDTO> error;

    public ServiceResponse(T response, HttpStatus status) {
        this.response = response;
        this.status = status;
    }


}
