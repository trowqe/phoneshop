package com.es.core.service.phone;

import com.es.core.dao.phone.ItemNotFoundException;
import com.es.core.dao.phone.PhoneDao;
import com.es.core.dao.phone.SortField;
import com.es.core.dao.phone.SortType;
import com.es.core.model.cart.Cart;
import com.es.core.model.phone.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneDao phoneDao;

    @Value("${findAll.limit}")
    private Integer limit;


    @Override
    public Phone get(Long id) {
        Optional<Phone> phone = phoneDao.get(id);
        if (!phone.isPresent()) throw new ItemNotFoundException("phone dao can't find phone with id: " + id);
        return phone.get();
    }

    @Override
    public List<Phone> paginatedPhoneList(int currentPage, String userInput, SortField sortField, SortType sortType) {

        int startItem = currentPage * limit;

        Optional<List<Phone>> phonesOptionalList = phoneDao.findAll(startItem, limit, userInput, sortField, sortType);
        if (phonesOptionalList.isPresent()) {
            return phonesOptionalList.get();
        } else throw new ItemNotFoundException("find all return empty list");
    }

    @Override
    public Long countTotalPages(String search) {
        return phoneDao.countAllPhones(search) % limit > 0 ? phoneDao.countAllPhones(search) / limit : phoneDao.countAllPhones(search) / limit + 1;
    }

    @Override
    public List<Phone> getPhonesByCart(Cart cart) {
        List<Phone> phones = new ArrayList<>();
        cart.getItems().forEach((id, quantity) -> {
            phones.add(phoneDao.get(id).get());
        });
        return phones;
    }

}


