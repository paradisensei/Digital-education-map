package org.doit.service.impl;

import org.doit.model.Organization;
import org.doit.repository.OrganizationRepository;
import org.doit.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Organization createOrUpdate(Organization organization) {
        return repository.save(organization);
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Organization get(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Organization> getAll() {
        return repository.findAll();
    }
}
