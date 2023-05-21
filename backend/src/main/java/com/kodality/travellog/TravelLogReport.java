package com.kodality.travellog;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TravelLogReport {
    private Map<LocalDate, List<TravelLog>> logsByDate;
    private double totalDistance;

    public TravelLogReport(Map<LocalDate, List<TravelLog>> logsByDate, double totalDistance) {
        this.logsByDate = logsByDate;
        this.totalDistance = totalDistance;
    }

    public TravelLogReport() {

    }

    public Map<LocalDate, List<TravelLog>> getLogsByDate() {
        return logsByDate;
    }

    public void setLogsByDate(Map<LocalDate, List<TravelLog>> logsByDate) {
        this.logsByDate = logsByDate;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }
}
