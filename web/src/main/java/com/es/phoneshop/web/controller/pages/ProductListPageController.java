package com.es.phoneshop.web.controller.pages;

import javax.annotation.Resource;

import com.es.core.dao.phone.SortType;
import com.es.core.model.cart.Cart;
import com.es.core.model.phone.Phone;
import com.es.core.service.phone.PhoneService;
import com.es.core.dao.phone.SortField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes({"userId", "cart"})
@RequestMapping(value = "/productList")
@PropertySource({"WEB-INF/conf/application.properties"})
public class ProductListPageController {
    @Resource
    private PhoneService phoneService;

    @Value("${findAll.limit}")
    private Integer limit;

    @GetMapping
    public String showProductList(Model model,
                                  @RequestParam(defaultValue = "PHONE_ID")  String sort,
                                  @RequestParam(defaultValue = "ASC") String type,
                                  @RequestParam(defaultValue = "") String userSearch) {

        System.out.println(sort + type + userSearch);

        List<Phone> phones = phoneService.findAll(0, 100, userSearch, SortField.valueOf(sort), SortType.valueOf(type));
        model.addAttribute("phones", phones);
        return "productList";
    }

    @ModelAttribute("cart")
    public Cart createCart() {
        Cart cart = new Cart();
        cart.initialize(1234L);
        return cart;
    }

}
