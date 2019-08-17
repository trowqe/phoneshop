package com.es.phoneshop.web.controller.pages;

import javax.annotation.Resource;

import com.es.core.dao.phone.SortType;
import com.es.core.service.phone.PhoneService;
import com.es.core.dao.phone.SortField;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping (value = "/productList")
public class ProductListPageController {
    @Resource
    private PhoneService phoneService;

    @RequestMapping(method = RequestMethod.GET)
    public String showProductList(Model model,
                                  @RequestParam(required = false) String sort,
                                  @RequestParam(required = false) String type,
                                  @RequestParam(required = false) String userSearch) {

        if (sort != null) {
            model.addAttribute("phones", phoneService.sort(SortField.valueOf(sort), SortType.valueOf(type)));
            return "productList";
        } else {
            model.addAttribute("phones", phoneService.findAll(0, 1000));
            return "productList";
        }
    }

}
