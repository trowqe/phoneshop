package com.es.core.dao.phone;

import com.es.core.model.phone.Color;
import com.es.core.model.phone.Phone;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PhoneResultSetExtractor implements ResultSetExtractor {

    @Override
    public List<Phone> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if (!resultSet.isBeforeFirst()) {
            throw new EmptyResultDataAccessException(0);
        } else {
            Set<Phone> phoneSet = new LinkedHashSet();
            Map<Long, Set<Color>> colorsMap = new HashMap();
            while (resultSet.next()) {
                Phone phone = new Phone();
                phone.setId(resultSet.getLong("id"));
                phone.setBrand(resultSet.getString("brand"));
                phone.setModel(resultSet.getString("model"));
                phone.setPrice(resultSet.getBigDecimal("price"));
                phone.setDisplaySizeInches(resultSet.getBigDecimal("displaySizeInches"));
                phone.setWeightGr(resultSet.getInt("weightGr"));
                phone.setLengthMm(resultSet.getBigDecimal("lengthMm"));
                phone.setWidthMm(resultSet.getBigDecimal("widthMm"));
                phone.setHeightMm(resultSet.getBigDecimal("heightMm"));
                phone.setAnnounced(resultSet.getDate("announced"));
                phone.setDeviceType(resultSet.getString("deviceType"));
                phone.setOs(resultSet.getString("os"));
                phone.setDisplayResolution(resultSet.getString("displayResolution"));
                phone.setPixelDensity(resultSet.getInt("pixelDensity"));
                phone.setDisplayTechnology(resultSet.getString("displayTechnology"));
                phone.setBackCameraMegapixels(resultSet.getBigDecimal("backCameraMegapixels"));
                phone.setFrontCameraMegapixels(resultSet.getBigDecimal("frontCameraMegapixels"));
                phone.setRamGb(resultSet.getBigDecimal("ramGb"));
                phone.setInternalStorageGb(resultSet.getBigDecimal("internalStorageGb"));
                phone.setBatteryCapacityMah(resultSet.getInt("batteryCapacityMah"));
                phone.setTalkTimeHours(resultSet.getBigDecimal("talkTimeHours"));
                phone.setStandByTimeHours(resultSet.getBigDecimal("standByTimeHours"));
                phone.setBluetooth(resultSet.getString("bluetooth"));
                phone.setPositioning(resultSet.getString("positioning"));
                phone.setImageUrl(resultSet.getString("imageUrl"));
                phone.setDescription(resultSet.getString("description"));

                phoneSet.add(phone);

                if (new Long(resultSet.getLong("colorId")).equals(null)) {
                } else {
                    Color color = new Color();
                    color.setId(resultSet.getLong("colorId"));
                    color.setCode(resultSet.getString("code"));

                    if (colorsMap.containsKey(phone.getId())) {
                        Set<Color> colorSet = colorsMap.get(phone.getId());
                        colorSet.add(color);
                        colorsMap.put(phone.getId(), colorSet);
                    } else {
                        Set<Color> colorSet = new HashSet();
                        colorSet.add(color);
                        colorsMap.put(phone.getId(), colorSet);
                    }
                }
            }
            List<Phone> phones = new ArrayList<>(phoneSet);
            for (Phone p : phones) {
                p.setColors(colorsMap.get(p.getId()));
            }
            return phones;
        }
    }
}
