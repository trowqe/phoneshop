package com.es.phoneshop.web.controller.pages;

import com.es.core.model.cart.Cart;
import com.es.phoneshop.web.controller.cart.CartView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
public class ExceptionController {

    @Autowired
    private CartView cartView;

    @Autowired
    private Cart cart;


    @ExceptionHandler(value = Exception.class)
    public String handleError(HttpServletRequest req, HttpServletResponse resp) {
        return "error";
    }


    @ModelAttribute
    public void handleRequest(HttpServletRequest request, Model model) {
        model.addAttribute("cartView", cartView);
        model.addAttribute("cart", cart);
    }
}



