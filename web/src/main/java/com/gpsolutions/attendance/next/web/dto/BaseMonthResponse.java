package com.gpsolutions.attendance.next.web.dto;

import java.util.List;

public class BaseMonthResponse implements MonthResponse {

    private final List<DayResponse> days;
    private final String difference;
    private final String total;

    public BaseMonthResponse(List<DayResponse> days, String difference, String total) {
        this.days = days;
        this.difference = difference;
        this.total = total;
    }

    public List<DayResponse> getDays() {
        return days;
    }

    public String getDifference() {
        return difference;
    }

    public String getTotal() {
        return total;
    }
}
