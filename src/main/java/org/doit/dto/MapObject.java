package org.doit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.doit.model.Address;
import org.doit.model.Contact;
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
            String organizationName = organization.getName();
            organization.getAddresses().forEach(address -> {
                Map<String, String> properties = new HashMap<>();
                properties.put("balloonContentHeader", String.format("<div class='balloonHeader'>%s</div>", organizationName));
                properties.put("balloonContentBody", String.format("<div class='balloonBody'>%s</div>", getBalloonBodyHtml(organization.getContacts(), address)));
                properties.put("hintContent", String.format("<div class='balloonHint'>%s</div>", organizationName));
                features.add(new Feature(address.getId(), new Geometry("Point",
                        new Double[]{address.getLatitude(), address.getLongitude()}), properties));
            });
        });
    }

    private String getBalloonBodyHtml(List<Contact> contacts, Address address) {
        return String.format("<div class='balloonAddress'>%s %s</div><div class='balloonContacts'>%s</div>",
                address.getCity(), address.getAddress(), getContactsHtml(contacts));
    }

    private String getContactsHtml(List<Contact> contacts) {
        StringBuilder contactsHtml = new StringBuilder();
        contacts.forEach(contact -> {
            String prefix;
            switch (contact.getType()) {
                case PHONE:
                    prefix = "tel:";
                    break;
                case EMAIL:
                    prefix = "mailto:";
                    break;
                default:
                    prefix = "";
            }
            contactsHtml.append(String.format("<div><a href='%s%2$s'>%2$s</a></div>", prefix, contact.getValue()));
        });

        return contactsHtml.toString();
    }

    @Data
    private static class Feature {

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
    private static class Geometry {

        private String type;

        private Double[] coordinates;
    }
}