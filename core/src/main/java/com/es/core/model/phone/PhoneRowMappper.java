package com.es.core.model.phone;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneRowMappper implements RowMapper<Phone> {
    @Override
    public Phone mapRow(ResultSet rs, int i) throws SQLException {
        Phone phone = null;
        phone.setId(rs.getLong("id"));
        phone.setBrand(rs.getString("brand"));
        phone.setModel(rs.getString(" model"));
        phone.setPrice(rs.getBigDecimal("price"));
        phone.setDisplaySizeInches(rs.getBigDecimal("displaySizeInches"));
        phone.setWeightGr(rs.getInt("weightGr"));
        phone.setLengthMm(rs.getBigDecimal("lengthMm"));
        phone.setWidthMm(rs.getBigDecimal("widthMm"));
        phone.setHeightMm(rs.getBigDecimal("heightMm"));
        phone.setAnnounced(rs.getDate("announced"));
        phone.setDeviceType(rs.getString("deviceType"));
        phone.setOs(rs.getString("os"));
        phone.setDisplayResolution(rs.getString("displayResolution"));
        phone.setPixelDensity(rs.getInt("pixelDensity"));
        phone.setDisplayTechnology(rs.getString("displayTechnology"));
        phone.setBackCameraMegapixels(rs.getBigDecimal("backCameraMegapixels"));
        phone.setFrontCameraMegapixels(rs.getBigDecimal("frontCameraMegapixels"));
        phone.setRamGb(rs.getBigDecimal("ramGb"));
        phone.setInternalStorageGb(rs.getBigDecimal("internalStorageGb"));
        phone.setBatteryCapacityMah(rs.getInt("batteryCapacityMah"));
        phone.setTalkTimeHours(rs.getBigDecimal("talkTimeHours"));
        phone.setStandByTimeHours(rs.getBigDecimal("standByTimeHours"));
        phone.setBluetooth(rs.getString("bluetooth"));
        phone.setPositioning(rs.getString("positioning"));
        phone.setImageUrl(rs.getString("imageUrl"));
        phone.setDescription(rs.getString("description"));

        return phone;
    }

}
