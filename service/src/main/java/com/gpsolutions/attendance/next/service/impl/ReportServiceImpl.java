package com.gpsolutions.attendance.next.service.impl;

import com.gpsolutions.attendance.client.AttendanceClient;
import com.gpsolutions.attendance.client.dto.AttendanceRequest;
import com.gpsolutions.attendance.next.model.DailyReport;
import com.gpsolutions.attendance.next.model.MonthlyReport;
import com.gpsolutions.attendance.next.model.User;
import com.gpsolutions.attendance.next.repository.DailyReportRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);

    //@Autowired
    @Qualifier("ldapUserDetailsService")
    private UserDetailsService userService;

    @Autowired
    private AttendanceClient client;

    @Autowired
    private DailyReportRepository dailyReportRepository;

    @Override
    public DailyReport getDailyReport(final LocalDate date) {
        final AttendanceRequest request = AttendanceRequestFactory.create(date, getAttendanceName());
        return ReportConverter.convert(client.getDayResponse(request));
    }

    @Override
    public MonthlyReport getMonthlyReport(final Month month) {
        final LocalDate now = LocalDate.now();
        final Year year = Year.now();

        final List<DailyReport> dailyReports = new ArrayList<>();
        dailyReports.addAll(dailyReportRepository.getByMonth(getUserUid(), month));
        for (int i = 0; i < month.length(year.isLeap()); i++) {
            final int dayOfMonth = i + 1;
            if (dailyReports.stream().anyMatch(dr -> dr.getDate().getDayOfMonth() == dayOfMonth)) {
                continue;
            }
            final DailyReport dailyReport = getDailyReport(
                    LocalDate.of(year.getValue(), month, dayOfMonth));
            dailyReports.add(dailyReport);
            if (now.getMonth().equals(month) && dayOfMonth == now.getDayOfMonth()) {
                break;
            }
            dailyReportRepository.save(getUserUid(), dailyReport);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                LOGGER.warn("Thread interrupted");
            }
        }
        return new MonthlyReport(dailyReports);
    }

    private String getAttendanceName() {
        final String ldapUid = getUserUid();
        //TODO load from hibernate cache
        return "prokopen";
        //return ((User)userService.loadUserByUsername(ldapUid)).getAttendanceName();
    }

    private String getUserUid() {
        return ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

}
