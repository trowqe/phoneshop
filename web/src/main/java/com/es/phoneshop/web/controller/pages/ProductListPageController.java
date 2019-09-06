package com.es.phoneshop.web.controller.pages;

import com.es.core.dao.phone.SortField;
import com.es.core.dao.phone.SortType;
import com.es.core.model.cartItem.CartItem;
import com.es.core.model.phone.Phone;
import com.es.core.service.phone.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping(value = "/productList")
public class ProductListPageController {
    @Resource
    private PhoneService phoneService;

    int pageSize = 10;


    @GetMapping
    public String showProductList(Model model,
                                  @RequestParam(defaultValue = "0") Integer page,
                                  @RequestParam(defaultValue = "PHONE_ID") String sort,
                                  @RequestParam(defaultValue = "ASC") String type,
                                  @RequestParam(defaultValue = "") String userSearch
    ) {

        List<Phone> phones = phoneService.paginatedPhoneList(pageSize, page, userSearch, SortField.valueOf(sort), SortType.valueOf(type));

        model.addAttribute("bookPage", phones);
        model.addAttribute("totalPages", 10);
        model.addAttribute("currentPage", page);

        model.addAttribute("cartItem", new CartItem());
        return "productList";
    }

}
