package com.gpsolutions.attendance.next.service.impl;

import com.gpsolutions.attendance.client.AttendanceClient;
import com.gpsolutions.attendance.client.dto.AttendanceRequest;
import com.gpsolutions.attendance.next.model.DailyReport;
import com.gpsolutions.attendance.next.model.MonthlyReport;
import com.gpsolutions.attendance.next.model.User;
import com.gpsolutions.attendance.next.service.ReportService;
import com.gpsolutions.attendance.next.util.AttendanceRequestFactory;
import com.gpsolutions.attendance.next.util.ReportConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private AttendanceClient client;

    @Override
    public DailyReport getDailyReport(final User user, final LocalDate date) {
        //TODO implement logic in user details
        final AttendanceRequest request = AttendanceRequestFactory.create(date, "prokopen"/*user.getAttendanceName()*/);
        return ReportConverter.convert(client.getDayResponse(request));
    }

    @Override
    public MonthlyReport getMonthlyReport(final User user, final Month month) {
        final LocalDate now = LocalDate.now();
        final Year year = Year.now();

        final List<DailyReport> dailyReports = new LinkedList<>();
        for (int i = 0; i < month.length(year.isLeap()); i++) {
            if (now.getMonth().equals(month) && i == now.getDayOfMonth()) {
                break;
            }
            final DailyReport dailyReport = getDailyReport(
                    user,
                    LocalDate.of(year.getValue(), month, i + 1));
            dailyReports.add(dailyReport);
        }
        return new MonthlyReport(dailyReports);
    }

}
