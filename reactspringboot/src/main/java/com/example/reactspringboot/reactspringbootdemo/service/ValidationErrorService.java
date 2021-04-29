package com.example.reactspringboot.reactspringbootdemo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationErrorService {
    public ResponseEntity<?> validataionError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            HashMap<String, String> map = new HashMap<>();
            for (FieldError f : bindingResult.getFieldErrors()) {
                map.put(f.getField(), f.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
        } else {
            return null;
        }
    }
}
