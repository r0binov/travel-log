package com.kodality.travellog;

import io.micronaut.core.annotation.Introspected;

import java.time.LocalDate;

@Introspected
public class TravelLog {
    private Long id;
    private String description;
    private LocalDate date;
    private String regNumber;
    private String ownerName;
    private int startOdometer;
    private int endOdometer;
    private String route;

    public TravelLog() {

    }

    public TravelLog(Long id, String description, LocalDate date, String regNumber, String ownerName, int startOdometer, int endOdometer, String route) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.regNumber = regNumber;
        this.ownerName = ownerName;
        this.startOdometer = startOdometer;
        this.endOdometer = endOdometer;
        this.route = route;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getStartOdometer() {
        return startOdometer;
    }

    public void setStartOdometer(int startOdometer) {
        this.startOdometer = startOdometer;
    }

    public int getEndOdometer() {
        return endOdometer;
    }

    public void setEndOdometer(int endOdometer) {
        this.endOdometer = endOdometer;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
