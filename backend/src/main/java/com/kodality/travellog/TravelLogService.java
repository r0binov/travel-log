package com.kodality.travellog;

import java.util.List;
import javax.inject.Singleton;

@Singleton
public class TravelLogService {

  public List<TravelLog> getTravelLogs() {
    TravelLog message = new TravelLog();
    message.setId(-1L);
    message.setDescription("Hello! This is dummy description that is hard-coded in TravelLogService." +
        " Instead of hard-coding you should fetch travel logs from real database using TravelLogRepository.");
    return List.of(message);
  }
}
