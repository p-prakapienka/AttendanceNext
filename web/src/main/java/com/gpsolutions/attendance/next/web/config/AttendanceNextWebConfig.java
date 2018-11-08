package com.gpsolutions.attendance.next.web.config;

import com.gpsolutions.attendance.next.web.mapper.DayResponseMapper;
import com.gpsolutions.attendance.next.web.mapper.MonthResponseMapper;
import com.gpsolutions.attendance.next.web.mapper.ResponseMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AttendanceNextWebConfig {

    @Bean
    public ResponseMapperFactory responseMapperFactory() {
        return new ResponseMapperFactory();
    }

    @Bean
    public DayResponseMapper dayResponseMapper() {
        return responseMapperFactory().baseDayResponseMapper();
    }

    @Bean
    public MonthResponseMapper monthResponseMapper() {
        return responseMapperFactory().baseMonthResponseMapper();
    }

}
