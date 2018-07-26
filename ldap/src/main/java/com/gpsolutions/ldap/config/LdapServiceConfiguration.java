package com.gpsolutions.ldap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;

@Configuration
@Import(LdapServerConfiguration.class)
public class LdapServiceConfiguration {

    @Autowired
    private BaseLdapPathContextSource contextSource;

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource);
    }

}
