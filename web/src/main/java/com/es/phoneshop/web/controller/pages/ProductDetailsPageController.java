package com.es.phoneshop.web.controller.pages;

import com.es.core.model.phone.Phone;
import com.es.core.service.phone.PhoneService;
import com.es.phoneshop.web.controller.cart.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/productDetails")
public class ProductDetailsPageController {

    @Resource
    private PhoneService phoneService;

    @GetMapping(value = "/{phoneId}")
    public String getPhoneDetails(Model model,
                                  @PathVariable Long phoneId){

        Phone phone = phoneService.get(phoneId);

        model.addAttribute("phone", phone);
        model.addAttribute("cartItem", new CartItem());
        return "productDetails";
    }
}
