package com.kodality.travellog;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller("/api")
public class TravelLogController {
    private final TravelLogService travelLogService;

    public TravelLogController(TravelLogService travelLogService) {
        this.travelLogService = travelLogService;
    }

    @Get("/travel-logs/report")
    public HttpResponse<TravelLogReport> generateReport(
            @QueryValue(value = "startDate") String startDate,
            @QueryValue(value = "endDate") String endDate,
            @QueryValue(value = "regNumber") String regNumber,
            @QueryValue(value = "ownerName") String ownerName) {
        TravelLogReport report = travelLogService.generateReport(startDate, endDate, regNumber, ownerName);
        return HttpResponse.ok(report);
    }

    @Get("/travel-logs")
    public List<TravelLog> getTravelLogs() {
        return travelLogService.getTravelLogs();
    }

    @Put("/travel-logs/{id}")
    public HttpResponse<TravelLog> updateTravelLog(@PathVariable Long id, @Body @Valid TravelLog updatedTravelLog) {
        return travelLogService.updateTravelLog(id, updatedTravelLog);
    }

    @Post("/travel-logs")
    public HttpResponse<TravelLog> createTravelLog(@Body @Valid TravelLog newTravelLog) {
        return travelLogService.createTravelLog(newTravelLog);
    }


    @Delete("/travel-logs/{id}")
    public HttpResponse deleteTravelLog(@PathVariable Long id) {
        return travelLogService.deleteTravelLog(id);
    }
}
