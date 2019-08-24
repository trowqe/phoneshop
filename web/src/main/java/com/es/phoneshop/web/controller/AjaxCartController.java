package com.es.phoneshop.web.controller;

import com.es.core.model.cart.Cart;
import com.es.core.model.cartItem.CartItem;
import com.es.core.service.cart.CartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@SessionAttributes({"userId", "cart"})
@RequestMapping(value = "/ajaxCart")

public class AjaxCartController {
    @Resource
    private CartService cartService;


    @GetMapping()
    public @ResponseBody
    String addToCart(@ModelAttribute("cart") Cart cart,
                     @RequestParam("phoneId") Long phoneId,
                     @Valid @RequestParam("quantity") Long quantity) throws Exception {
        CartItem cartItem = new CartItem(phoneId, quantity);
        cart.addItem(cartItem);
        return "productList";
    }

}
