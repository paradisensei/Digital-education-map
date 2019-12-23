package org.doit.service;

import org.doit.model.Organization;

import java.util.List;

public interface OrganizationService {

    Organization createOrUpdate(Organization organization);

    void delete(long id) throws RuntimeException;

    Organization get(long id);

    List<Organization> getAll();
}
