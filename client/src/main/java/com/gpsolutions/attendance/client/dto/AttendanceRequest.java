package com.gpsolutions.attendance.client.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class AttendanceRequest implements Serializable {

    private String username;
    private LocalDate date;

    public AttendanceRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
