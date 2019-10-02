package com.es.phoneshop.web.controller.pages;

import com.es.core.model.phone.Phone;
import com.es.core.service.phone.PhoneService;
import com.es.phoneshop.web.controller.cart.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productDetails")
public class ProductDetailsPageController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/{phoneId}")
    public String getPhoneDetails(Model model,
                                  @PathVariable Long phoneId){

        Phone phone = phoneService.get(phoneId);

        model.addAttribute("phone", phone);
        model.addAttribute("cartItem", new CartItem());
        return "productDetails";
    }
}
