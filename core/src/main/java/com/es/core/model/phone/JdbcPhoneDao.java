
package com.es.core.model.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JdbcPhoneDao implements PhoneDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Phone> get(final Long key) {
        try {
            Phone phone = (Phone) jdbcTemplate.queryForObject("select * from phones where id = ?", new Object[]{key}, new BeanPropertyRowMapper(Phone.class));
            phone.setColors(getColorSetByPhoneId(key));
            return Optional.of(phone);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Phone phone) {
        if (phone.getId() == null) {
            create(phone);
        } else {
            update(phone);
        }
    }

    private Set<Color> getColorSetByPhoneId(Long phoneId) {
        List<Color> colorsList = jdbcTemplate.query(
                "select id, code from colors where id in (select colorId from phone2color where phoneId= ? )",
                new Object[]{phoneId}, new BeanPropertyRowMapper(Color.class));
        return colorsList.stream()
                .collect(Collectors.toSet());
    }

    private void create(final Phone phone) {
        jdbcTemplate.update("insert into phones (brand, model, price, displaySizeInches, weightGr, lengthMm, " +
                        "widthMm, heightMm, announced, deviceType, os, displayResolution, pixelDensity, " +
                        "displayTechnology, backCameraMegapixels, frontCameraMegapixels, ramGb, internalStorageGb, " +
                        "batteryCapacityMah, talkTimeHours, standByTimeHours, bluetooth, positioning, imageUrl, " +
                        "description) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
                phoneGettersWithoutId(phone));
    }

    private void update(final Phone phone) {
        jdbcTemplate.update("insert into phones (brand, model, price, displaySizeInches, weightGr, lengthMm, " +
                        "widthMm, heightMm, announced, deviceType, os, displayResolution, pixelDensity, " +
                        "displayTechnology, backCameraMegapixels, frontCameraMegapixels, ramGb, internalStorageGb, " +
                        "batteryCapacityMah, talkTimeHours, standByTimeHours, bluetooth, positioning, imageUrl, " +
                        "description) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) where phoneId=?",
                phoneGettersWithoutId(phone), phone.getId());
    }

    private Object[] phoneGettersWithoutId(Phone phone) {
        Object[] arr = {phone.getBrand(),
                phone.getModel(),
                phone.getPrice(),
                phone.getDisplaySizeInches(),
                phone.getWeightGr(),
                phone.getLengthMm(),
                phone.getWidthMm(),
                phone.getHeightMm(),
                phone.getAnnounced(),
                phone.getDeviceType(),
                phone.getOs(),
                phone.getDisplayResolution(),
                phone.getPixelDensity(),
                phone.getDisplayTechnology(),
                phone.getBackCameraMegapixels(),
                phone.getFrontCameraMegapixels(),
                phone.getRamGb(),
                phone.getInternalStorageGb(),
                phone.getBatteryCapacityMah(),
                phone.getTalkTimeHours(),
                phone.getStandByTimeHours(),
                phone.getBluetooth(),
                phone.getPositioning(),
                phone.getImageUrl(),
                phone.getDescription()};
        return arr;
    }

    public List<Phone> findAll(int offset, int limit) {
        List<Phone> phones = jdbcTemplate.query("select * from phones offset " + offset + " limit " + limit, new BeanPropertyRowMapper(Phone.class));
        phones.forEach(p -> p.setColors(getColorSetByPhoneId(p.getId())));
        return phones;
    }

    @Override
    public List<Phone> sortByBrand(SortType sortType) {
        List<Phone> phones = jdbcTemplate.query("select * from phones order by brand " + sortType.type,
                new BeanPropertyRowMapper(Phone.class));
        phones.forEach(p -> p.setColors(getColorSetByPhoneId(p.getId())));
        return phones;
    }

    @Override
    public List<Phone> sortByModel(SortType sortType) {
        List<Phone> phones = jdbcTemplate.query("select * from phones order by model " + sortType.type,
                new BeanPropertyRowMapper(Phone.class));
        phones.forEach(p -> p.setColors(getColorSetByPhoneId(p.getId())));
        return phones;
    }

    @Override
    public List<Phone> sortByDisplaySize(SortType sortType) {
        List<Phone> phones = jdbcTemplate.query("select * from phones order by displaySizeInches " + sortType.type,
                new BeanPropertyRowMapper(Phone.class));
        phones.forEach(p -> p.setColors(getColorSetByPhoneId(p.getId())));
        return phones;
    }

    @Override
    public List<Phone> sortByPrise(SortType sortType) {
        List<Phone> phones = jdbcTemplate.query("select * from phones order by price " + sortType.type,
                new BeanPropertyRowMapper(Phone.class));
        phones.forEach(p -> p.setColors(getColorSetByPhoneId(p.getId())));
        return phones;
    }

    @Override
    public List<Phone> userSearchByModel(String searchString) {
        List<Phone> phones = jdbcTemplate.query("select * from phones where model like '%" + searchString.trim() + "%'",
                new BeanPropertyRowMapper(Phone.class));
        phones.forEach(p -> p.setColors(getColorSetByPhoneId(p.getId())));
        return phones;
    }
}
