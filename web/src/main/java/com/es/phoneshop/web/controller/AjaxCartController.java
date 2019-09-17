package com.es.phoneshop.web.controller;

import com.es.core.service.cart.CartService;
import com.es.phoneshop.web.controller.cart.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/ajaxCart")

public class AjaxCartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addPhone(@Valid CartItem cartItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();

            bindingResult.getFieldErrors().stream()
                    .forEach(e -> errorMessages.add(messageSource.getMessage(e, null)));

            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        } else {
            cartService.addPhone(cartItem.getItemId(), cartItem.getItemQuantity());
            Map<String, Object> map = new HashMap<>();
            map.put("totalItem", cartService.countTotalItem());
            map.put("totalSum", cartService.countTotalSum());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

}
