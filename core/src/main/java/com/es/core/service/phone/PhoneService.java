package com.es.core.service.phone;

import com.es.core.dao.phone.SortField;
import com.es.core.dao.phone.SortType;
import com.es.core.model.cart.Cart;
import com.es.core.model.phone.Phone;

import java.util.List;

public interface PhoneService {
    Phone get(Long id);

    List<Phone> paginatedPhoneList(int currentPage, String userInput, SortField sortField, SortType sortType);

    Long countTotalPages(String search);

    List<Phone> getPhonesByCart(Cart cart);
}
