package com.kodality.travellog;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TravelLogRowMapper implements RowMapper<TravelLog> {

    @Override
    public TravelLog mapRow(ResultSet rs, int rowNum) throws SQLException {
        TravelLog travelLog = new TravelLog(
                rs.getLong("id"),
                rs.getString("description"),
                rs.getDate("date").toLocalDate(),
                rs.getString("reg_number"),
                rs.getString("owner_name"),
                rs.getInt("start_odometer"),
                rs.getInt("end_odometer"),
                rs.getString("route")
        );
        return travelLog;
    }
}
