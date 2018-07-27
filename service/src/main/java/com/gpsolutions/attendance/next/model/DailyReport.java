package com.gpsolutions.attendance.next.model;

import java.time.LocalDate;
import java.util.List;

public class DailyReport {

    private LocalDate date;
    private List<FloorAttendance> floorAttendances;

    public DailyReport() {
    }

    public List<FloorAttendance> getFloorAttendances() {
        return floorAttendances;
    }

    public void setFloorAttendances(List<FloorAttendance> floorAttendances) {
        this.floorAttendances = floorAttendances;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
