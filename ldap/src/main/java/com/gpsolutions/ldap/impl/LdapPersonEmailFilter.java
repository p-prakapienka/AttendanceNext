package com.gpsolutions.ldap.impl;

import com.gpsolutions.ldap.LdapPerson;
import com.gpsolutions.ldap.function.LdapPersonFilter;

public class LdapPersonEmailFilter implements LdapPersonFilter {

    private final String domains;

    public LdapPersonEmailFilter(String domains) {
        this.domains = domains;
    }

    @Override
    public boolean filter(LdapPerson ldapPerson) {
        return domains.contains(ldapPerson.getEmail().split("@")[1]);
    }
}
