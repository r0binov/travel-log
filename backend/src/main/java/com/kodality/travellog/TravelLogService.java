package com.kodality.travellog;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;

import javax.inject.Singleton;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class TravelLogService {
    private final TravelLogRepository travelLogRepository;

    public TravelLogService(TravelLogRepository travelLogRepository) {
        this.travelLogRepository = travelLogRepository;
    }

    public List<TravelLog> getTravelLogs() {
        return travelLogRepository.getAllTravelLogs();
    }

    public HttpResponse<TravelLog> updateTravelLog(@PathVariable Long id, @Body @Valid TravelLog updatedTravelLog) {
        TravelLog existingLog = travelLogRepository.findById(id);
        if (existingLog == null) {
            return HttpResponse.notFound();
        }
        updatedTravelLog.setId(id);
        TravelLog updatedLog = travelLogRepository.update(updatedTravelLog);
        URI uri = URI.create("/api/travel-logs/" + updatedLog.getId());
        return HttpResponse.ok(updatedLog).headers(headers -> headers.location(uri));
    }

    public HttpResponse<TravelLog> createTravelLog(@Body @Valid TravelLog newTravelLog) {
        Long nextId = travelLogRepository.findLastId() + 1;
        newTravelLog.setId(nextId);
        TravelLog log = travelLogRepository.save(newTravelLog);
        URI uri = URI.create("/api/travel-logs/" + log.getId());
        return HttpResponse.created(log, uri);
    }

    public HttpResponse<TravelLog> deleteTravelLog(@PathVariable Long id) {
        TravelLog existingTravelLogOptional = travelLogRepository.findById(id);
        if (existingTravelLogOptional != null) {
            travelLogRepository.deleteTravelLog(id);
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound();
        }
    }

    public TravelLogReport generateReport(String startDate, String endDate, String regNumber, String ownerName) {
        String sql = "SELECT * FROM travel_log WHERE 1 = 1";
        List<Object> parameters = new ArrayList<>();

        if (startDate != null) {
            sql += " AND date >= CAST(? AS DATE)";
            parameters.add(startDate);
        }

        if (endDate != null) {
            sql += " AND date <= CAST(? AS DATE)";
            parameters.add(endDate);
        }

        if (regNumber != null && !regNumber.isEmpty()) {
            sql += " AND reg_number = ?";
            parameters.add(regNumber);
        }

        if (ownerName != null && !ownerName.isEmpty()) {
            sql += " AND owner_name = ?";
            parameters.add(ownerName);
        }

        sql += " ORDER BY date, start_odometer";

        List<TravelLog> travelLogs = travelLogRepository.query(sql, parameters.toArray(), new TravelLogRowMapper());

        Map<LocalDate, List<TravelLog>> logsByDate = travelLogs.stream().collect(Collectors.groupingBy(TravelLog::getDate));

        logsByDate.forEach((date, logs) -> logs.sort(Comparator.comparing(TravelLog::getStartOdometer)));

        double totalDistance = travelLogs.stream().mapToDouble(log -> log.getEndOdometer() - log.getStartOdometer()).sum();

        return new TravelLogReport(logsByDate, totalDistance);
    }
}

