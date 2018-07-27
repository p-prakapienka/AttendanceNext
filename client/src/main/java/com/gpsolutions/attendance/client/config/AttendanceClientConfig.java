package com.gpsolutions.attendance.client.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AttendanceClientConfiguration.class)
public @interface AttendanceClientConfig {
}
