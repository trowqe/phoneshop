package com.es.phoneshop.web.controller.pages;

import com.es.core.dao.phone.ItemNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
@EnableWebMvc
public class ExceptionController {

    @ExceptionHandler(value = ItemNotFoundException.class)
    public String handleError(HttpServletRequest req, HttpServletResponse resp){
        return "error";
    }
}



