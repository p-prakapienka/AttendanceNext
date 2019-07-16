package com.gpsolutions.attendance.client.config;

import com.gpsolutions.attendance.client.AttendanceClient;
import com.gpsolutions.attendance.client.enumeration.Mode;
import com.gpsolutions.attendance.client.impl.AttendanceHtmlClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AttendanceClientConfiguration {

    @Value("${attendance.url}")
    private String rootUri;

    @Value("${attendance.client.mode:LOCAL}")
    private String mode;

    @Value("${attendance.client.retry.count:3}")
    private Integer retryCount;

    @Bean
    public AttendanceClient attendanceClient() {
        return new AttendanceHtmlClient();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .rootUri(rootUri)
                //TODO message util
                .additionalMessageConverters(
                        new FormHttpMessageConverter(),
                        new StringHttpMessageConverter())
                .build();
    }

    @Bean
    public DynamicClientConfiguration dynamicClientConfiguration() {
        return new DynamicClientConfiguration(
                Mode.REMOTE.name().equals(mode)
                        ? Mode.REMOTE
                        : Mode.LOCAL,
                retryCount);
    }

}
