package com.gpsolutions.attendance.next.service;

import com.gpsolutions.attendance.next.model.DailyReport;
import com.gpsolutions.attendance.next.model.MonthlyReport;

import java.time.LocalDate;
import java.time.Month;

public interface ReportService {

    DailyReport getDailyReport(final LocalDate date);

    MonthlyReport getMonthlyReport(final Month month);

}
