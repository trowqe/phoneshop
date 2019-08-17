package com.es.core.dao.phone;

import com.es.core.model.phone.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JdbcPhoneDao implements PhoneDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Phone> get(final Long key) {
        String SQL = "SELECT * FROM phones LEFT JOIN phone2color ON phones.id=phone2color.phoneId " +
                "LEFT JOIN colors ON phone2color.colorId = colors.id " +
                "WHERE phones.price > 0 AND phones.id = ? AND "+
                "((SELECT stocks.stock FROM stocks WHERE stocks.phoneId = ? ) > 0 )";
        try {
        List<Phone> list = (List<Phone>) jdbcTemplate.query(SQL, new Object[]{key, key}, new PhoneResultSetExtractor());
        return Optional.ofNullable(list.get(0));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Long save(Phone phone) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String SQL = "INSERT INTO phones (brand, model, price, displaySizeInches, weightGr, lengthMm, " +
                "widthMm, heightMm, announced, deviceType, os, displayResolution, pixelDensity, " +
                "displayTechnology, backCameraMegapixels, frontCameraMegapixels, ramGb, internalStorageGb, " +
                "batteryCapacityMah, talkTimeHours, standByTimeHours, bluetooth, positioning, imageUrl, " +
                "description) " +
                "VALUES (:brand, :model, :price, :displaySizeInches, :weightGr, :lengthMm, " +
                ":widthMm, :heightMm, :announced, :deviceType, :os, :displayResolution, :pixelDensity, " +
                ":displayTechnology, :backCameraMegapixels, :frontCameraMegapixels, :ramGb, :internalStorageGb, " +
                ":batteryCapacityMah, :talkTimeHours, :standByTimeHours, :bluetooth, :positioning, :imageUrl, " +
                ":description)";

        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(phone);
        namedParameterJdbcTemplate.update(SQL, namedParameters, keyHolder, new String[]{"id"});
        return (Long) keyHolder.getKey();
    }

    public List<Phone> findAll(int offset, int limit) {
       String SQL = "SELECT * FROM phones LEFT JOIN phone2color ON phones.id=phone2color.phoneId " +
                "LEFT JOIN colors ON phone2color.colorId = colors.id " +
                "WHERE phones.price > 0 AND " +
                "((SELECT stocks.stock FROM stocks WHERE stocks.phoneId = phones.id) > 0 )" +
            "OFFSET " + offset + " LIMIT " + limit;
       try {
           List<Phone> phones = (List<Phone>) jdbcTemplate.query(SQL, new PhoneResultSetExtractor());
           return phones;
       } catch (EmptyResultDataAccessException e) {
           return new ArrayList<>();
       }
    }

    @Override
    public List<Phone> sortByField(SortField sortField, SortType sortType, int limit) {
        String SQL = "SELECT * FROM phones LEFT JOIN phone2color ON phones.id=phone2color.phoneId " +
                "LEFT JOIN colors ON phone2color.colorId = colors.id " +
                "WHERE phones.price > 0 AND " +
                "((SELECT stocks.stock FROM stocks WHERE stocks.phoneId = phones.id) > 0 )" +
                "ORDER BY UPPER( " +sortField.field + " ) " + sortType.type + " LIMIT " + limit;
        try {
            List<Phone> phones = (List<Phone>) jdbcTemplate.query(SQL, new PhoneResultSetExtractor());
            return phones;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Phone> userSearchByModel(String searchString, int limit) {
        String SQL = "SELECT * FROM phones LEFT JOIN phone2color ON phones.id=phone2color.phoneId " +
                "LEFT JOIN colors ON phone2color.colorId = colors.id " +
                "WHERE phones.price > 0 AND " +
                "((SELECT stocks.stock FROM stocks WHERE stocks.phoneId = phones.id) > 0 ) " +
                "AND model LIKE '%" + searchString.trim() + "%'";
        try {
            List<Phone> phones = (List<Phone>) jdbcTemplate.query(SQL, new PhoneResultSetExtractor());
            return phones;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}