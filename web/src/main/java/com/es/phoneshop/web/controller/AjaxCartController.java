package com.es.phoneshop.web.controller;

import com.es.core.model.cart.Cart;
import com.es.core.model.cartItem.CartItem;
import com.es.core.service.cart.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@SessionAttributes({"userId", "cart"})
@RequestMapping(value = "/ajaxCart")

public class AjaxCartController {
    @Resource
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Object> addPhone (@RequestBody @Valid CartItem cartItem, BindingResult bindingResult) {
        System.out.println("in ajax controller");
        if(bindingResult.hasErrors()){
            System.out.println("eeeeee");
        }
        System.out.println(cartItem.getItemId());
        System.out.println(cartItem.getItemQuantity());
        cartService.addPhone(cartItem.getItemId(), cartItem.getItemQuantity());
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
