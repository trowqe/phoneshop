package com.es.core.model.phone;

import java.util.List;
import java.util.Optional;

public interface PhoneDao {
    Optional<Phone> get(Long key);
    void save(Phone phone);
    List<Phone> findAll(int offset, int limit);

    List<Phone> sortByBrand(SortType sortType);

    List<Phone> sortByModel(SortType sortType);

    List<Phone> sortByDisplaySize(SortType sortType);

    List<Phone> sortByPrise(SortType sortType);

    List<Phone> userSearchByModel(String searchString);
}
