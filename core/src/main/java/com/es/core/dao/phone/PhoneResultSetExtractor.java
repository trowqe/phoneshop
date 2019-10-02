package com.es.core.dao.phone;

import com.es.core.model.phone.Color;
import com.es.core.model.phone.Phone;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PhoneResultSetExtractor implements ResultSetExtractor<Optional<Phone>> {

    private BeanPropertyRowMapper<Phone> phoneMapper = new BeanPropertyRowMapper<>(Phone.class);
    private BeanPropertyRowMapper<Color> colorMapper = new BeanPropertyRowMapper<>(Color.class);

    @Override
    public Optional<Phone> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if (!resultSet.next()) {
            return Optional.empty();
        }

        Phone phone = phoneMapper.mapRow(resultSet, resultSet.getRow());
        phone.setId(resultSet.getLong("id"));

        if (resultSet.getString("code") != null) {
            Set<Color> colors = new HashSet<>();
            do {
                colors.add(colorMapper.mapRow(resultSet, resultSet.getRow()));
            } while (resultSet.next());

            phone.setColors(colors);
        }

        return Optional.of(phone);
    }
}


