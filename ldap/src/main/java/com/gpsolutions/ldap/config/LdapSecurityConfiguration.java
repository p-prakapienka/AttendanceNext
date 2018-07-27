package com.gpsolutions.ldap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.search.LdapUserSearch;

@Configuration
@EnableWebSecurity
@Import(LdapServerConfiguration.class)
public class LdapSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${ldap.searchBase}")
    private String searchBase;

    @Value("${ldap.searchFilter}")
    private String searchFilter;

    @Autowired
    private BaseLdapPathContextSource contextSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authenticationProvider())
                .eraseCredentials(false);
    }

    private LdapAuthenticationProvider authenticationProvider() {
        //TODO authoritiesPopulator
        return new LdapAuthenticationProvider(bindAuthenticator());
    }

    private BindAuthenticator bindAuthenticator() {
        final BindAuthenticator bindAuthenticator = new BindAuthenticator(contextSource);
        bindAuthenticator.setUserSearch(userSearch());
        return bindAuthenticator;
    }

    private LdapUserSearch userSearch() {
        return new FilterBasedLdapUserSearch(searchBase, searchFilter, contextSource);
    }

}
