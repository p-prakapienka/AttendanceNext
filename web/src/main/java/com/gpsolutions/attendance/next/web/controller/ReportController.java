package com.gpsolutions.attendance.next.web.controller;

import com.gpsolutions.attendance.next.model.DailyReport;
import com.gpsolutions.attendance.next.model.MonthlyReport;
import com.gpsolutions.attendance.next.model.User;
import com.gpsolutions.attendance.next.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/day")
    public DailyReport day(final Authentication authentication) {
        return reportService.getDailyReport(extractUser(authentication), LocalDate.now());
    }

    @GetMapping("/day/{date}")
    public DailyReport day(final Authentication authentication,
                           @PathVariable("date")
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                           final LocalDate date) {
        return reportService.getDailyReport(extractUser(authentication), date);
    }

    @GetMapping("/month")
    public MonthlyReport month(final Authentication authentication) {
        return reportService.getMonthlyReport(extractUser(authentication), LocalDate.now().getMonth());
    }

    @GetMapping("/month/{number}")
    public MonthlyReport month(final Authentication authentication,
                               @PathVariable("number") final Integer month) {
        return reportService.getMonthlyReport(extractUser(authentication), Month.of(month));
    }

    private User extractUser(final Authentication authentication) {
        return null;
//        return (User) authentication.getPrincipal();
    }

}
