package com.gpsolutions.attendance.next.web.controller;

import com.gpsolutions.attendance.next.model.DailyReport;
import com.gpsolutions.attendance.next.model.MonthlyReport;
import com.gpsolutions.attendance.next.service.ReportService;
import com.gpsolutions.attendance.next.web.dto.DayResponse;
import com.gpsolutions.attendance.next.web.dto.MonthResponse;
import com.gpsolutions.attendance.next.web.mapper.DayResponseMapper;
import com.gpsolutions.attendance.next.web.mapper.MonthResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private DayResponseMapper dayMapper;

    @Autowired
    private MonthResponseMapper monthMapper;

    @Autowired
    private ReportService reportService;

    @GetMapping("/day")
    public ResponseEntity<DayResponse> day() {
        return day(LocalDate.now());
    }

    @GetMapping("/day/{date}")
    public ResponseEntity<DayResponse> day(@PathVariable("date")
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                           final LocalDate date) {
        final DailyReport report = reportService.getDailyReport(date);

        return ResponseEntity.ok(dayMapper.map(report));
    }

    @GetMapping("/month")
    public ResponseEntity<MonthResponse> month() {
        return month(LocalDate.now().getMonth().getValue());
    }

    @GetMapping("/month/{number}")
    public ResponseEntity<MonthResponse> month(@PathVariable("number") final Integer month) {
        final MonthlyReport report = reportService.getMonthlyReport(Month.of(month));

        return ResponseEntity.ok(monthMapper.map(report));
    }

}
