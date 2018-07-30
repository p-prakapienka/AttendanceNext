package com.gpsolutions.attendance.next.web.dto;

import java.util.List;

public class BaseMonthResponse implements MonthResponse {

    private final List<DayResponse> days;
    private final String difference;

    public BaseMonthResponse(List<DayResponse> days, String difference) {
        this.days = days;
        this.difference = difference;
    }

    public List<DayResponse> getDays() {
        return days;
    }

    public String getDifference() {
        return difference;
    }
}
