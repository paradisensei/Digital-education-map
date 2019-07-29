package org.doit.controller;

import org.doit.model.Organization;
import org.doit.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "/admin/login";
    }

    @PostMapping(value = "/organizations", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String create(@RequestParam String name, @RequestParam String description) {
        organizationService.create(new Organization(name, description));
        return "redirect:" + ADMIN_URL;
    }
}
