package com.gpsolutions.ldap;

import com.gpsolutions.ldap.function.LdapPersonFilter;
import com.gpsolutions.ldap.function.LdapPersonMapper;

import java.util.List;
import java.util.Optional;

public interface LdapService<T> {

    Optional<LdapPerson> getByUid(final String uid);

    T getByUid(final String uid, final LdapPersonMapper<T> mapper);

    List<LdapPerson> searchByUid(final String uid);

    List<T> searchByUid(final String uid, final LdapPersonMapper<T> mapper);

    List<LdapPerson> getAll();

    List<LdapPerson> getAll(final LdapPersonFilter filter);

    List<T> getAll(final LdapPersonMapper<T> mapper);

    List<T> getAll(final LdapPersonMapper<T> mapper, final LdapPersonFilter filter);

}
