package org.doit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.doit.model.Address;
import org.doit.model.ContactType;
import org.doit.model.Organization;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class MapObject {

    private String type = "FeatureCollection";

    private Set<Feature> features = new HashSet<>();

    public MapObject(List<Organization> organizations) {
        organizations.forEach(organization -> {
            Map<ContactType, String> contacts = organization.getContacts();
            String organizationName = organization.getName();
            organization.getAddresses().forEach(address -> {
                Map<String, String> properties = new HashMap<>();
                properties.put("balloonContentHeader", String.format("<div class='balloonHeader'>%s</div>", organizationName));
                properties.put("balloonContentBody", String.format("<div class='balloonBody'>%s</div>", getBalloonBodyHtml(contacts, address)));
                properties.put("hintContent", String.format("<div class='balloonHint'>%s</div>", organizationName));
                features.add(new Feature(address.getId(), new Geometry("Point",
                        new Double[]{address.getLatitude(), address.getLongitude()}), properties));
            });
        });
    }

    private String getBalloonBodyHtml(Map<ContactType, String> contacts, Address address) {
        return String.format("<div class='balloonAddress'>%s %s</div><div class='balloonContacts'>%s</div>",
                address.getCity(), address.getAddress(), getContactsHtml(contacts));
    }

    private String getContactsHtml(Map<ContactType, String> contacts) {
        StringBuilder contactsHtml = new StringBuilder();
        contacts.forEach((contactType, value) -> {
            String prefix;
            switch (contactType) {
                case PHONE:
                    prefix = "tel:";
                    break;
                case EMAIL:
                    prefix = "mailto:";
                    break;
                default:
                    prefix = "";
            }
            contactsHtml.append(String.format("<div><a href='%s%s'>%s</a></div>", prefix, value, value));
        });

        return contactsHtml.toString();
    }

    @Data
    private class Feature {

        private long id;

        private String type = "future";

        private Geometry geometry;

        private Map<String, String> properties;

        Feature(long id, Geometry geometry, Map<String, String> properties) {
            this.id = id;
            this.geometry = geometry;
            this.properties = properties;
        }
    }

    @Data
    @AllArgsConstructor
    private class Geometry {

        private String type;

        private Double[] coordinates;
    }
}