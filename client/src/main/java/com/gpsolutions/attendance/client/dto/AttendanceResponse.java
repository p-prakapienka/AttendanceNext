package com.gpsolutions.attendance.client.dto;

import java.io.Serializable;
import java.util.List;

public class AttendanceResponse implements Serializable {

    private AttendanceRequest request;
    private List<Period> periods;

    public AttendanceResponse() {
    }

    public AttendanceRequest getRequest() {
        return request;
    }

    public void setRequest(AttendanceRequest request) {
        this.request = request;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }
}
