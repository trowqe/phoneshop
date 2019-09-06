package com.es.phoneshop.web.controller.pages;

import com.es.core.model.cartItem.CartItem;
import com.es.core.model.phone.Phone;
import com.es.core.service.phone.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
@EnableWebMvc
@RequestMapping(value = "/productDetails")
public class ProductDetailsPageController {

    @Resource
    private PhoneService phoneService;

    @GetMapping(value = "/{phoneId}")
    public String getPhoneDetails(Model model,
                                  @PathVariable Optional<Long> phoneId){

        Long id = phoneId.isPresent() ? phoneId.get() : 1000L;
        System.out.println(id);
        Phone phone = phoneService.get(id);
        System.out.println("aaaaaaaa");
        model.addAttribute("phone", phone);
        model.addAttribute("cartItem", new CartItem());
        return "productDetails";
    }
}
