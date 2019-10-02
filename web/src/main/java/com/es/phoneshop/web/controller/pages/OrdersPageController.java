package com.es.phoneshop.web.controller.pages;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderStatus;
import com.es.core.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class OrdersPageController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String allOrders(Model model){
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "adminOrders";
    }

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrderByOrderId(orderId);
        model.addAttribute("order", order);
        return "adminOrder";
    }


    @PutMapping("/{orderId}")
    public String oderInfo( @PathVariable Long orderId, @RequestParam("orderStatus") OrderStatus orderStatus, Model model){
        orderService.updateStatusById(orderStatus, orderId);
        return "redirect:/admin/orders/"+orderId;
    }
}
