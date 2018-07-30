package com.gpsolutions.attendance.next.web.dto;

import java.time.LocalDate;
import java.util.Map;

public class BaseDayResponse implements DayResponse {

    private final LocalDate date;
    private final String total;
    private final Map<String, String> timePerFloor;

    public BaseDayResponse(LocalDate date, String total, Map<String, String> timePerFloor) {
        this.date = date;
        this.total = total;
        this.timePerFloor = timePerFloor;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTotal() {
        return total;
    }

    public Map<String, String> getTimePerFloor() {
        return timePerFloor;
    }

}
