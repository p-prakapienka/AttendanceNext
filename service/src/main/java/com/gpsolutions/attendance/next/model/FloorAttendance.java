package com.gpsolutions.attendance.next.model;

import java.time.Duration;

public class FloorAttendance {

    private Floor floor;
    private Duration duration;

    public FloorAttendance() {
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

}
