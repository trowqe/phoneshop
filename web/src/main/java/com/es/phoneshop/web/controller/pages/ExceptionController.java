package com.es.phoneshop.web.controller.pages;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = Exception.class)
    public String handleError(HttpServletRequest req, HttpServletResponse resp){
        return "error";
    }
}



