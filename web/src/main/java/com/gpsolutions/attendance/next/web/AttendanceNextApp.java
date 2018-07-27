package com.gpsolutions.attendance.next.web;

import com.gpsolutions.attendance.next.config.AttendanceServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AttendanceServiceConfiguration.class)
public class AttendanceNextApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceNextApp.class, args);
    }

}
