package com.gpsolutions.attendance.client.dto;

import java.io.Serializable;
import java.time.LocalTime;

public class Period implements Serializable {

    private LocalTime timeIn;
    private LocalTime timeOut;
    private Floor floor;

    public Period() {
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}
