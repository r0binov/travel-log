package com.kodality.travellog;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.List;

@Controller("/api")
public class TravelLogController {
  private final TravelLogService travelLogService;

  public TravelLogController(TravelLogService travelLogService) {
    this.travelLogService = travelLogService;
  }

  @Get("travel-logs")
  public List<TravelLog> getTravelLogs() {
    return travelLogService.getTravelLogs();
  }
}
