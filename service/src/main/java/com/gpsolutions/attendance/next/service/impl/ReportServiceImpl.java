package com.gpsolutions.attendance.next.service.impl;

import com.gpsolutions.attendance.client.AttendanceClient;
import com.gpsolutions.attendance.client.dto.AttendanceRequest;
import com.gpsolutions.attendance.next.model.DailyReport;
import com.gpsolutions.attendance.next.model.MonthlyReport;
import com.gpsolutions.attendance.next.model.User;
import com.gpsolutions.attendance.next.service.ReportService;
import com.gpsolutions.attendance.next.util.AttendanceRequestFactory;
import com.gpsolutions.attendance.next.util.ReportConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    @Qualifier("ldapUserDetailsService")
    private UserDetailsService userService;

    @Autowired
    private AttendanceClient client;

    @Override
    public DailyReport getDailyReport(final LocalDate date) {
        final AttendanceRequest request = AttendanceRequestFactory.create(date, getAttendanceName());
        return ReportConverter.convert(client.getDayResponse(request));
    }

    @Override
    public MonthlyReport getMonthlyReport(final Month month) {
        final LocalDate now = LocalDate.now();
        final Year year = Year.now();

        final List<DailyReport> dailyReports = new LinkedList<>();
        for (int i = 0; i < month.length(year.isLeap()); i++) {
            if (now.getMonth().equals(month) && i == now.getDayOfMonth()) {
                break;
            }
            final DailyReport dailyReport = getDailyReport(
                    LocalDate.of(year.getValue(), month, i + 1));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                LOGGER.warn("Thread interrupted");
            }
            dailyReports.add(dailyReport);
        }
        return new MonthlyReport(dailyReports);
    }

    private String getAttendanceName() {
        final String ldapUid = ((UserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername();
        //TODO load from hibernate cache
        return ((User)userService.loadUserByUsername(ldapUid)).getAttendanceName();
    }

}
