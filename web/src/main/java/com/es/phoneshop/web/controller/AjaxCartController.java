package com.es.phoneshop.web.controller;

import com.es.core.model.cart.Cart;
import com.es.core.model.cartItem.CartItem;
import com.es.core.service.cart.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/ajaxCart")

public class AjaxCartController {
    @Resource
    private CartService cartService;


    @PostMapping
    public ResponseEntity<Object> addPhone(@Valid @RequestBody CartItem cartItem, BindingResult bindingResult) {
        //@ModelAttribute("cartItem") CartItem cartItem --> 500 errorz
        System.out.println("in ajax controller");
        if (bindingResult.hasErrors()) {
            System.out.println("BINDING");
            String errorMessage = bindingResult.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
        } else {
            cartService.addPhone(cartItem.getItemId(), cartItem.getItemQuantity());
            System.out.println(cartService.countTotalItem() + "---" + cartService.countTotalSum());
            Map<String, Object> map = new HashMap();
            map.put("totalItem", cartService.countTotalItem());
            map.put("totalSum", cartService.countTotalSum());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

}
