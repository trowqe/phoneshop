package com.es.phoneshop.web.controller;

import com.es.core.service.cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller


@RequestMapping(value = "/ajaxCart" )

public class AjaxCartController {
    @Resource
    private CartService cartService;

    @GetMapping
    @ResponseBody
    public String addPhone(@RequestParam String phoneId, @RequestParam String quantity) {
        Long id = Long.valueOf(phoneId);
        Long q = Long.valueOf(quantity);
        cartService.addPhone(id, q);
        return cartService.getCart().toString();
    }
}
