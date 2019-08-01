package org.doit.controller;

import org.doit.dto.MapObject;
import org.doit.model.Organization;
import org.doit.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestOrganizationController {

    private final OrganizationService service;

    @Autowired
    public RestOrganizationController(OrganizationService service) {
        this.service = service;
    }

    @GetMapping(value = "/rest/organizations")
    public MapObject getAllWithAddressesAndContacts() {
        return new MapObject(service.getAllWithAddressesAndContacts());
    }

    @GetMapping("/rest/organization/{id}")
    public Organization get(@PathVariable long id) {
        return service.get(id);
    }
}