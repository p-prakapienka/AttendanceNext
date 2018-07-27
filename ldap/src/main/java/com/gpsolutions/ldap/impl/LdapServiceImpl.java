package com.gpsolutions.ldap.impl;

import com.gpsolutions.ldap.LdapPerson;
import com.gpsolutions.ldap.LdapService;
import com.gpsolutions.ldap.function.LdapPersonFilter;
import com.gpsolutions.ldap.function.LdapPersonMapper;
import com.gpsolutions.ldap.function.LdapPersonMerger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

public class LdapServiceImpl<T> implements LdapService<T> {

    private static final LdapContextMapper LDAP_MAPPER = new LdapContextMapper();

    private static final LdapPersonFilter DEFAUT_FILTER = (ldapPerson) -> true;

    @Value("${ldap.searchBase}")
    private String searchBase;

    @Autowired
    private LdapTemplate ldapTemplate;

    @Override
    public Optional<LdapPerson> getByUid(String uid) {
        return ldapTemplate
                .search(query()
                            .base(searchBase)
                            .where("uid").is(uid),
                        LDAP_MAPPER)
                .stream().findFirst();
    }

    @Override
    public T getByUid(String uid, LdapPersonMapper<T> mapper) {
        return mapper.map(
                        getByUid(uid).orElseThrow(() ->
                                new NameNotFoundException(String.format("Uid %s not found", uid)))
        );
    }

    @Override
    public List<LdapPerson> searchByUid(String uid) {
        return ldapTemplate
                .search(query()
                            .base(searchBase)
                            .where("uid").like(uid),
                        LDAP_MAPPER);
    }

    @Override
    public List<T> searchByUid(String uid, LdapPersonMapper<T> mapper) {
        return searchByUid(uid).stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public List<LdapPerson> getAll() {
        return ldapTemplate.search(
                query()
                    .base(searchBase)
                    .where("objectClass").is("person")
                    .and("loginShell").is("/bin/bash")
                    .and("mail").isPresent(),
                LDAP_MAPPER);
    }

    @Override
    public List<LdapPerson> getAll(final LdapPersonFilter filter) {
        return getAll().stream().filter(filter::filter).collect(Collectors.toList());
    }

    @Override
    public List<T> getAll(final LdapPersonMapper<T> mapper) {
        return getAll(mapper, DEFAUT_FILTER);
    }

    @Override
    public List<T> getAll(final LdapPersonMapper<T> mapper, final LdapPersonFilter filter) {
        return getAll().stream()
                    .filter(filter::filter)
                    .map(mapper::map)
                    .collect(Collectors.toList());
    }

    private static class LdapContextMapper extends AbstractContextMapper<LdapPerson> {
        @Override
        protected LdapPerson doMapFromContext(DirContextOperations dirContextOperations) {
            return new LdapPerson(){{
                setCn(dirContextOperations.getStringAttribute("cn"));
                setUid(dirContextOperations.getStringAttribute("uid"));
                setEmail(dirContextOperations.getStringAttribute("msil"));
            }};
        }
    }
}
