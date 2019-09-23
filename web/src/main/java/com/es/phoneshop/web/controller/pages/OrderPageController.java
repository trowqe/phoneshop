package com.es.phoneshop.web.controller.pages;

import com.es.core.model.cart.Cart;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.order.OutOfStockException;
import com.es.core.service.order.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrderPageController {
    @Resource
    private OrderService orderService;

    private List<OrderItem> orderItems;

    @GetMapping
    public String getOrder(@ModelAttribute Cart cart,
                           Model model) {
        Order order = orderService.createOrder(cart);
        orderItems = order.getOrderItems();
        model.addAttribute("order", order);

        return "order";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String placeOrder(@Valid Order order, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "order";
        }

        for (OrderItem item: orderItems) {
            item.setOrder(order);
        }
        order.setOrderItems(orderItems);

        Long id;
        try {
           id =  orderService.placeOrder(order);
            order.setId(id);
            model.addAttribute("order", order);
            return "redirect:/orderOverview/"+id;
        } catch (OutOfStockException e) {
           return "error";
        }

    }
}
