package com.gpsolutions.attendance.next.web;

import com.gpsolutions.attendance.client.config.AttendanceClientConfig;
import com.gpsolutions.ldap.config.EnableGpSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableGpSecurity
@AttendanceClientConfig
public class AttendanceNextApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceNextApp.class, args);
    }

}
