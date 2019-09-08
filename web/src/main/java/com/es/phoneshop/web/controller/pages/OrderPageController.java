package com.es.phoneshop.web.controller.pages;

import com.es.core.model.cart.Cart;
import com.es.core.model.order.OutOfStockException;
import com.es.core.service.order.OrderService;
import com.es.phoneshop.web.controller.cart.CartView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/order")
public class OrderPageController {
    @Resource
    private OrderService orderService;

   @GetMapping
    public String getOrder(@ModelAttribute CartView cartView,
                           @ModelAttribute Cart cart,
                           Model model) {
       // orderService.createOrder(null);
       return "order";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void placeOrder() throws OutOfStockException {
        orderService.placeOrder(null);
    }
}
