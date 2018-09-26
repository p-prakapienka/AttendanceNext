package com.gpsolutions.attendance.next.web.dto;

import java.time.LocalDate;
import java.util.Map;

public class BaseDayResponse implements DayResponse {

    private final LocalDate date;
    private final String timeInOffice;
    private final String total;
    private final Map<String, String> timePerFloor;

    public BaseDayResponse(LocalDate date, String timeInOffice, String total, Map<String, String> timePerFloor) {
        this.date = date;
        this.timeInOffice = timeInOffice;
        this.total = total;
        this.timePerFloor = timePerFloor;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTimeInOffice() {
        return timeInOffice;
    }

    public String getTotal() {
        return total;
    }

    public Map<String, String> getTimePerFloor() {
        return timePerFloor;
    }

}
