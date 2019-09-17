package com.es.phoneshop.web.controller.pages;

import com.es.core.dao.phone.ItemNotFoundException;
import com.es.core.model.cart.Cart;
import com.es.phoneshop.web.controller.cart.CartView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;


@RestControllerAdvice
public class ControllerAdvice {

    @Autowired
    private CartView cartView;

    @Autowired
    private Cart cart;


    @ExceptionHandler(ItemNotFoundException.class)
    public ModelAndView handleItemNotFoundException(ItemNotFoundException ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("message", ex.getMessage());
        model.addObject("statusCode", 404);
        return model;
    }


    @ModelAttribute
    public void handleRequest(Model model) {
        model.addAttribute("cartView", cartView);
    }
}



