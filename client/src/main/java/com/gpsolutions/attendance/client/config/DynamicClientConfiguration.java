package com.gpsolutions.attendance.client.config;

import com.gpsolutions.attendance.client.enumeration.Mode;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class DynamicClientConfiguration {

    @Value("${attendance.url}")
    private String url;

    private Mode mode;
    private Integer retryCount;

    public DynamicClientConfiguration(Mode mode, Integer retryCount) {
        this.mode = mode;
        this.retryCount = retryCount;
    }

    @PostConstruct
    public void init() {
        if (url == null) throw new IllegalStateException("Attendance URL not specified");
    }

    public String getUrl() {
        return url;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

}
