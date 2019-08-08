package com.es.core.model.phone;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JdbcPhoneDao implements PhoneDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public Optional<Phone> get(final Long key) {
        Phone phone = jdbcTemplate.queryForObject("select * from phones where id = ?", new Object[]{key}, new PhoneRowMappper());

        phone.setColors(getColorSet(key));  // говно какое-то

        return Optional.ofNullable(phone);
    }

    private List<Long> getPhoneColorsIdList(Long phoneId) {
        return jdbcTemplate.query("select colorId from phone2color where phoneId=?",
                (resultSet, i) -> resultSet.getLong("colorId"), phoneId);
    }

    private Color getColorCodeById(Long colorId) {
        return jdbcTemplate.queryForObject(
                "select code from colors where id = ?",
                new Object[]{colorId}, (resultSet, i) -> {
                    Color color = new Color();
                    color.setId(colorId);
                    color.setCode(resultSet.getString("code"));
                    return color;
                });
    }

    private Set<Color> getColorSet(Long phoneId) {
        List<Long> phoneColorsIdList = getPhoneColorsIdList(phoneId);

        return phoneColorsIdList.stream()
                .map(this::getColorCodeById)
                .collect(Collectors.toSet());
    }

    public void save(final Phone phone) {
        throw new UnsupportedOperationException("TODO");
    }

    public List<Phone> findAll(int offset, int limit) {
        //return jdbcTemplate.query("select * from phones offset " + offset + " limit " + limit, new BeanPropertyRowMapper(Phone.class));
        List<Phone> phones = jdbcTemplate.query("select * from phones offset " + offset + " limit " + limit, new BeanPropertyRowMapper(Phone.class));
        phones.forEach(p -> p.setColors(getColorSet(p.getId())));
        return phones;
    }
}

