package com.es.phoneshop.web.controller.pages;

import com.es.core.model.cart.Cart;
import com.es.core.model.phone.Phone;
import com.es.core.service.cart.CartService;
import com.es.core.service.phone.PhoneService;
import com.es.phoneshop.web.controller.cart.CartItem;
import com.es.phoneshop.web.controller.cart.CartItemForm;
import com.es.phoneshop.web.controller.cart.CartView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/cartPage")
public class CartPageController {
    @Resource
    private CartService cartService;

    @Resource
    private PhoneService phoneService;

    @GetMapping
    public String getCart(Model model, @ModelAttribute Cart cart,
                          @ModelAttribute CartView cartView) {
        CartItemForm cartItemForm = new CartItemForm();

        Map<Long, CartItem> cartItems = getWebCartItemsFromCartService();
        cartItemForm.setCartItems(cartItems);
        model.addAttribute("cartItemForm", cartItemForm);

        List<Phone> phones = phoneService.getPhonesByCart(cart);
        model.addAttribute("phones", phones);

        cartView.setTotalItems(cartService.countTotalItem());
        cartView.setTotalSum(cartService.countTotalSum());

        return "cartPage";
    }

    @PostMapping(value = "/update")
    public String updateCart(
            @Valid CartItemForm cartItemForm,
            BindingResult bindingResultItem,
            @ModelAttribute CartView cartView,
            Model model) {

        Cart cart = cartService.getCart();
        List<Phone> phones = phoneService.getPhonesByCart(cart);
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
        model.addAttribute("cartItemsForm", cartItemForm);

        cartView.setTotalItems(cartService.countTotalItem());
        cartView.setTotalSum(cartService.countTotalSum());

        return "cartPage";
    }

    @PostMapping(value = "/delete")
    public String delete2(@RequestParam Long phoneId,
                          @ModelAttribute CartView cartView,
                          @ModelAttribute Cart cart,
                          @ModelAttribute CartItemForm cartItemForm,
                          Model model) {

        cartService.remove(phoneId);

        List<Phone> phones = phoneService.getPhonesByCart(cart);
        model.addAttribute("phones", phones);

        Map<Long, CartItem> cartItems = getWebCartItemsFromCartService();
        cartItemForm.setCartItems(cartItems);
        model.addAttribute("cartItemsForm", cartItemForm);

        cartView.setTotalItems(cartService.countTotalItem());
        cartView.setTotalSum(cartService.countTotalSum());
        Map<String, Object> map = new HashMap();
        map.put("totalItem", cartService.countTotalItem());
        map.put("totalSum", cartService.countTotalSum());

        return "cartPage";
    }

    private Map<Long, CartItem> getWebCartItemsFromCartService() {
        Map<Long, Long> cartItemsMap = cartService.getCart().getItems();
        Map<Long, CartItem> cartItems = new HashMap<>();
        for (Long id : cartItemsMap.keySet()
        ) {
            cartItems.put(id, new CartItem(id, cartItemsMap.get(id)));
        }
        return cartItems;
    }

}