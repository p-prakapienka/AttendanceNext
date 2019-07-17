package com.gpsolutions.attendance.next.config;

import com.gpsolutions.attendance.client.config.AttendanceClientConfig;
import com.gpsolutions.ldap.config.EnableGpSecurity;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableGpSecurity
@AttendanceClientConfig
@ComponentScan("com.gpsolutions.attendance.next")
public class AttendanceServiceConfiguration {
}
