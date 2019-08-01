package org.doit.controller;

import org.doit.model.Organization;
import org.doit.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(AdminController.ADMIN_URL)
public class AdminController {

    public static final String ADMIN_URL = "/admin";

    private final OrganizationService organizationService;

    @Autowired
    public AdminController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("organizations", organizationService.getAll());
        return ADMIN_URL + "/index";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return ADMIN_URL + "/login";
    }

    @PostMapping(value = "/organizations", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String create(@RequestParam long id, @RequestParam String name, @RequestParam String description) {
        organizationService.createOrUpdate(new Organization(id, name, description));
        return "redirect:" + ADMIN_URL;
    }

    @DeleteMapping("/organizations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("id") long id) {
        organizationService.delete(id);
    }
}
