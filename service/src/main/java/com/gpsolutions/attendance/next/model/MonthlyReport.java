package com.gpsolutions.attendance.next.model;

import java.util.List;

public class MonthlyReport {

    private List<DailyReport> dailyReports;

    public MonthlyReport() {
    }

    public MonthlyReport(List<DailyReport> dailyReports) {
        this.dailyReports = dailyReports;
    }

    public List<DailyReport> getDailyReports() {
        return dailyReports;
    }

    public void setDailyReports(List<DailyReport> dailyReports) {
        this.dailyReports = dailyReports;
    }

}
