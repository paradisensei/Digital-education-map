package org.doit.controller;

import org.doit.model.Organization;
import org.doit.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class RestOrganizationController {

    private final OrganizationService service;

    @Autowired
    public RestOrganizationController(OrganizationService service) {
        this.service = service;
    }

    @GetMapping(value = "/rest/organizations")
    public Collection<Organization> getAll() {
        return service.getAll();
    }

    @GetMapping("/rest/organization/{id}")
    public Organization get(@PathVariable long id) {
        return service.get(id);
    }
}