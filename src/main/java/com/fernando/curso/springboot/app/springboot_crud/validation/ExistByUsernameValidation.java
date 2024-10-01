package com.fernando.curso.springboot.app.springboot_crud.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fernando.curso.springboot.app.springboot_crud.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistByUsernameValidation implements ConstraintValidator<ExistByUsername, String> {

    @Autowired
    private UserService productService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        if(productService == null){
            return true;
        }
        return !productService.existsByUsername(username);
    }

}
