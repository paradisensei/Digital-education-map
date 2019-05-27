package org.doit.model;

public enum OrganizationCategory {

    PARENT("Родитель"),
    TEACHER("Учитель"),
    BUSINESS("Предприниматель");

    private final String title;

    OrganizationCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
