package com.es.phoneshop.web.controller.pages;

import com.es.core.model.phone.Phone;
import com.es.core.service.cart.CartService;
import com.es.phoneshop.web.controller.cart.CartItem;
import com.es.phoneshop.web.controller.cart.CartItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/cartPage")
public class CartPageController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public String getCart(Model model) {
        CartItemForm cartItemForm = new CartItemForm();

        Map<Long, CartItem> cartItems = getWebCartItemsFromCartService();
        cartItemForm.setCartItems(cartItems);
        model.addAttribute("cartItemForm", cartItemForm);

        List<Phone> phones = cartService.getPhonesInCart();
        model.addAttribute("phones", phones);

        return "cartPage";
    }

    @PutMapping
    public String updateCart(
            @Valid CartItemForm cartItemForm,
            BindingResult bindingResultItem,
            Model model) {

        List<Phone> phones = cartService.getPhonesInCart();
        model.addAttribute("phones", phones);

        if (bindingResultItem.hasErrors()) {
            return "cartPage";
        }

        Map<Long, CartItem> itemsFromForm = cartItemForm.getCartItems();
        Map<Long, Long> itemsToUpdate = new HashMap<>();
        itemsFromForm.forEach((k, v) -> itemsToUpdate.put(k, v.getItemQuantity()));

        cartService.update(itemsToUpdate);

        Map<Long, CartItem> cartItems = getWebCartItemsFromCartService();
        cartItemForm.setCartItems(cartItems);
        model.addAttribute("cartItemForm", cartItemForm);

        return "redirect:/cartPage";
    }


    private Map<Long, CartItem> getWebCartItemsFromCartService() {
        Map<Long, Long> cartItemsMap = cartService.getCart().getItems();
        return cartItemsMap.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e-> new CartItem(e.getKey(), e.getValue())
                        ));
    }

}