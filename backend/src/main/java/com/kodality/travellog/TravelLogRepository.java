package com.kodality.travellog;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Singleton;
import javax.sql.DataSource;
import java.util.List;

@Singleton
public class TravelLogRepository {
    private final JdbcTemplate jdbcTemplate;

    public TravelLogRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<TravelLog> query(String sql, Object[] parameters, TravelLogRowMapper rowMapper) {
        return jdbcTemplate.query(sql, parameters, rowMapper);
    }

    public List<TravelLog> getAllTravelLogs() {
        String sql = "SELECT * FROM travel_log ORDER BY id ASC;";
        return jdbcTemplate.query(sql, new TravelLogRowMapper());
    }

    public TravelLog findById(Long id) {
        String sql = "SELECT * FROM travel_log WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new TravelLogRowMapper());
    }

    public Long findLastId() {
        String sql = "SELECT max(id) FROM travel_log";
        Long maxId = jdbcTemplate.queryForObject(sql, Long.class);
        return maxId == null ? 0L : maxId;
    }

    public TravelLog update(TravelLog log) {
        String sql = "UPDATE travel_log SET date = ?, reg_number = ?, owner_name = ?, start_odometer = ?, end_odometer = ?, route = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, log.getDate(), log.getRegNumber(), log.getOwnerName(), log.getStartOdometer(), log.getEndOdometer(), log.getRoute(), log.getDescription(), log.getId());
        return log;
    }

    public TravelLog save(TravelLog log) {
        Long nextId = jdbcTemplate.queryForObject("SELECT COALESCE(MAX(id), 0) + 1 FROM travel_log", Long.class);
        log.setId(nextId);
        String sql = "INSERT INTO travel_log (id, date, reg_number, owner_name, start_odometer, end_odometer, route, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, log.getId(), log.getDate(), log.getRegNumber(), log.getOwnerName(), log.getStartOdometer(), log.getEndOdometer(), log.getRoute(), log.getDescription());
        return log;
    }

    public void deleteTravelLog(Long id) {
        String sql = "DELETE FROM travel_log WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
