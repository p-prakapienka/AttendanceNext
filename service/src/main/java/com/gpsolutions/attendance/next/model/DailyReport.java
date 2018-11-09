package com.gpsolutions.attendance.next.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class DailyReport implements Comparable<DailyReport> {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DailyReport)) return false;
        DailyReport that = (DailyReport) o;
        return getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate());
    }

    @Override
    public int compareTo(DailyReport o) {
        return this.getDate().compareTo(o.getDate());
    }
}
