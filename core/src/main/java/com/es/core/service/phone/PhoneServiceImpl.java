package com.es.core.service.phone;

import com.es.core.dao.phone.ItemNotFoundException;
import com.es.core.model.phone.Phone;
import com.es.core.dao.phone.PhoneDao;
import com.es.core.dao.phone.SortField;
import com.es.core.dao.phone.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        if(!phone.isPresent()) throw new ItemNotFoundException("phone dao can't find phone with id: " + id);
        return phone.get();
    }

    @Override
    public List<Phone> findAll(int offset, String userInput, SortField sortField, SortType sortType) {
        Optional<List<Phone>> phonesOptionalList = phoneDao.findAll(offset, limit, userInput, sortField, sortType);
        books = phonesOptionalList.get();
        if (phonesOptionalList.isPresent()) {
            return phonesOptionalList.get();
        } else throw new ItemNotFoundException("find all return empty list");
    }

    private List<Phone> books = null;

    @Override
    public Page<Phone> paginatePhoneList(Pageable pageable, List<Phone> phones) {

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        if (books.size() < startItem) {
            phones = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            phones = books.subList(startItem, toIndex);
        }

        Page<Phone> bookPage
                = new PageImpl<Phone>(phones, PageRequest.of(currentPage, pageSize), books.size());

        return bookPage;
    }

}


