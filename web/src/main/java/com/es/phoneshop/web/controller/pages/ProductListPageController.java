package com.es.phoneshop.web.controller.pages;

import javax.annotation.Resource;

import com.es.core.dao.phone.ItemNotFoundException;
import com.es.core.dao.phone.SortType;
import com.es.core.model.cart.Cart;
import com.es.core.model.cartItem.CartItem;
import com.es.core.model.phone.Phone;
import com.es.core.service.phone.PhoneService;
import com.es.core.dao.phone.SortField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@EnableWebMvc
@SessionAttributes({"userId", "cart"})
@RequestMapping(value = "/productList")
public class ProductListPageController {
    @Resource
    private PhoneService phoneService;

    int size = 10;


    @GetMapping(value = "/page/{page}")
    public String showProductList(Model model,
                                  @PathVariable Optional<Integer> page,
                                  @RequestParam(defaultValue = "PHONE_ID") String sort,
                                  @RequestParam(defaultValue = "ASC") String type,
                                  @RequestParam(defaultValue = "") String userSearch
    ) {

        Integer curPage = page.isPresent() ? page.get() : 1;

        List<Phone> phones = phoneService.findAll(0, userSearch, SortField.valueOf(sort), SortType.valueOf(type));

        int pageSize = size;

        Page<Phone> phonePage = phoneService.paginatePhoneList(PageRequest.of(curPage - 1, pageSize), phones);

        model.addAttribute("bookPage", phonePage);

        int totalPages = phonePage.getTotalPages();

        if(curPage>totalPages){
            throw new ItemNotFoundException("wrong page number");
        }

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("cartItem", new CartItem(0L, 0L));

        return "productList";
    }

}
