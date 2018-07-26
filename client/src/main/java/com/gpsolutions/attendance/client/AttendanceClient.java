package com.gpsolutions.attendance.client;

import com.gpsolutions.attendance.client.dto.AttendanceRequest;
import com.gpsolutions.attendance.client.dto.AttendanceResponse;
import com.gpsolutions.attendance.client.util.AttendanceResponseMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.format.DateTimeFormatter;

public class AttendanceClient {

    private static final String DATE_KEY = "calendar";
    private static final String USER_KEY = "user";

    @Value("${attendance.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public AttendanceResponse getForDate(final AttendanceRequest request) {
        final ResponseEntity<String> response = restTemplate.postForEntity(url, createEntity(request), String.class);

        return AttendanceResponseMessageConverter.convert(request, response.getBody());
    }

    private HttpEntity<MultiValueMap<String, String>> createEntity(final AttendanceRequest request) {
        return new HttpEntity<>(createBody(request), createHeaders());
    }

    private MultiValueMap<String, String> createBody(final AttendanceRequest request) {
        return new LinkedMultiValueMap<String, String>() {{
            add(DATE_KEY, request.getDate().format(DateTimeFormatter.ISO_DATE));
            add(USER_KEY, request.getUsername());
        }};
    }

    private HttpHeaders createHeaders(){
        final String authHeader = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getHeader("Authorization");
        return new HttpHeaders() {{
            set("Authorization", authHeader);
            setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }};
    }

}
