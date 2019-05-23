package org.doit.service;

import org.doit.model.Organization;

import java.util.List;

public interface OrganizationService {

    Organization create(Organization organization);

    void delete(Long id) throws RuntimeException;

    Organization get(Long id);

    List<Organization> getAll();
}
