package com.gpsolutions.attendance.next.config;

import com.gpsolutions.attendance.client.config.AttendanceClientConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@AttendanceClientConfig
@ComponentScan(basePackages = {
        "com.gpsolutions.attendance.next.service.**",
        "com.gpsolutions.attendance.next.repository.*"
})
public class AttendanceServiceConfiguration {
}
