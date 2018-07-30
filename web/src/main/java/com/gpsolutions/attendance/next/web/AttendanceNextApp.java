package com.gpsolutions.attendance.next.web;

import com.gpsolutions.attendance.next.config.AttendanceServiceConfiguration;
import com.gpsolutions.attendance.next.model.FloorAttendance;
import com.gpsolutions.attendance.next.web.config.AttendanceNextWebConfig;
import com.gpsolutions.attendance.next.web.dto.BaseDayResponse;
import com.gpsolutions.attendance.next.web.dto.BaseMonthResponse;
import com.gpsolutions.attendance.next.web.dto.DayResponse;
import com.gpsolutions.attendance.next.web.mapper.DayResponseMapper;
import com.gpsolutions.attendance.next.web.mapper.MonthResponseMapper;
import com.gpsolutions.attendance.next.web.mapper.ResponseMapperFactory;
import com.gpsolutions.attendance.next.web.util.DateTimeUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
@Import({
        AttendanceServiceConfiguration.class,
        AttendanceNextWebConfig.class
})
public class AttendanceNextApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceNextApp.class, args);
    }

}
