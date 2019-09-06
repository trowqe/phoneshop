package com.es.core.dao.phone;

import com.es.core.model.phone.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcPhoneDao implements PhoneDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private static final String SQL_GET_PHONE_BY_ID = "SELECT * FROM phones LEFT JOIN phone2color ON " +
            "phones.id=phone2color.phoneId " +
            "LEFT JOIN colors ON phone2color.colorId = colors.id " +
            "WHERE phones.price > 0 AND phones.id = :searchId AND " +
            "((SELECT SUM(stocks.stock) FROM stocks WHERE stocks.phoneId = :searchId ) > 0 )";

    private static final String SQL_SAVE_PHONE = "INSERT INTO phones (brand, model, price, displaySizeInches, weightGr, lengthMm, " +
            "widthMm, heightMm, announced, deviceType, os, displayResolution, pixelDensity, " +
            "displayTechnology, backCameraMegapixels, frontCameraMegapixels, ramGb, internalStorageGb, " +
            "batteryCapacityMah, talkTimeHours, standByTimeHours, bluetooth, positioning, imageUrl, " +
            "description) " +
            "VALUES (:brand, :model, :price, :displaySizeInches, :weightGr, :lengthMm, " +
            ":widthMm, :heightMm, :announced, :deviceType, :os, :displayResolution, :pixelDensity, " +
            ":displayTechnology, :backCameraMegapixels, :frontCameraMegapixels, :ramGb, :internalStorageGb, " +
            ":batteryCapacityMah, :talkTimeHours, :standByTimeHours, :bluetooth, :positioning, :imageUrl, " +
            ":description)";


    public Optional<Phone> get(final Long key) {

        Map<String, Long> paramMap = new HashMap<>();
        paramMap.put("searchId", key);

        return namedParameterJdbcTemplate.query(SQL_GET_PHONE_BY_ID, paramMap, new PhoneResultSetExtractor());
    }

    @Override
    public Long save(Phone phone) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(phone);
        namedParameterJdbcTemplate.update(SQL_SAVE_PHONE, namedParameters, keyHolder, new String[]{"id"});
        return keyHolder.getKey().longValue();
    }


    public Optional findAll(int offset, int limit, String searchString, SortField sortField, SortType sortType) {

        String SQL_FIND_ALL = "SELECT * FROM phones " +
                "WHERE phones.price > 0 AND " +
                "((SELECT SUM(stocks.stock) FROM stocks WHERE stocks.phoneId = phones.id) > 0 )" +
                " AND model LIKE '%" + searchString.trim() + "%' " +
                "ORDER BY UPPER( " + sortField.field + " ) " + sortType.type + " LIMIT " + limit + " OFFSET " + offset;

        return Optional.of(namedParameterJdbcTemplate.
                query(SQL_FIND_ALL, new BeanPropertyRowMapper(Phone.class)));
    }

    @Override
    public Optional countTotalPriceByPhoneIds(List<Long> idList) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("phonesIds", idList);

        Map<Long, BigDecimal> map =
                namedParameterJdbcTemplate.query("SELECT id, price FROM phones WHERE id IN (:phonesIds)",
                        parameters, resultSet -> {
                            Map<Long, BigDecimal> resMap = new HashMap<>();
                            while (resultSet.next()) {
                                resMap.put(resultSet.getLong("id"),
                                        resultSet.getBigDecimal("price"));
                            }
                            return resMap;
                        });
        if (map.size() > 0) {
            return Optional.of(map);
        } else return Optional.empty();
    }
}