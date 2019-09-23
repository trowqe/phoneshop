package com.es.phoneshop.web.controller.pages;

import com.es.core.dao.phone.SortField;
import com.es.core.dao.phone.SortType;
import com.es.core.model.phone.Phone;
import com.es.core.service.phone.PhoneService;
import com.es.phoneshop.web.controller.cart.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/productList")
public class ProductListPageController {
    @Resource
    private PhoneService phoneService;


    @GetMapping
    public String showProductList(Model model,
                                  @RequestParam(defaultValue = "0") Integer page,
                                  @RequestParam(defaultValue = "PHONE_ID") String sort,
                                  @RequestParam(defaultValue = "ASC") String type,
                                  @RequestParam(defaultValue = "") String userSearch
    ) {
        List<Phone> phones = phoneService.paginatedPhoneList(page, userSearch, SortField.valueOf(sort), SortType.valueOf(type));

        model.addAttribute("bookPage", phones);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", phoneService.countTotalPages(userSearch));

        model.addAttribute("cartItem", new CartItem());
        return "productList";
    }

}
