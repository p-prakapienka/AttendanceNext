package com.gpsolutions.attendance.next.service;

import com.gpsolutions.attendance.next.model.DailyReport;
import com.gpsolutions.attendance.next.model.MonthlyReport;
import com.gpsolutions.attendance.next.model.User;

import java.time.LocalDate;
import java.time.Month;

public interface ReportService {

    DailyReport getDailyReport(final User user, final LocalDate date);

    MonthlyReport getMonthlyReport(final User user, final Month month);

}
