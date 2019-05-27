package org.doit.model;

public enum ContactType {

    PHONE("Тел."),
    EMAIL("E-Mail"),
    URL("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
