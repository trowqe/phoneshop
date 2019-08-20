package com.es.phoneshop.web.controller.pages;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.es.core.dao.phone.SortType;
import com.es.core.model.phone.Phone;
import com.es.core.service.phone.PhoneService;
import com.es.core.dao.phone.SortField;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/productList")
public class ProductListPageController {
    @Resource
    private PhoneService phoneService;

    @GetMapping
    public String showProductList(Model model,
                                  @RequestParam(required = false) String sort,
                                  @RequestParam(required = false) String type,
                                  @RequestParam(required = false) String userSearch) {

        if (sort == null) {
            sort = "PHONE_ID";
        }
        if (type == null) {
            type = "ASC";
        }
        if (userSearch == null) {
            userSearch = "";
        }

        System.out.println(sort + type + userSearch);

        List<Phone> phones = phoneService.findAll(0, 1000, userSearch, SortField.valueOf(sort), SortType.valueOf(type));
        System.out.println(phones.size());
        model.addAttribute("phones", phones);
        return "productList";
    }

}
