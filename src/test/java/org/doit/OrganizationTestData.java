package org.doit;

import org.doit.model.*;

import java.util.*;

public class OrganizationTestData {
    public static final Long ORGANIZATION_1_ID = 14L;
    public static final Long ORGANIZATION_DELETE_ID = 1000L;

    private static final Map<ContactType, String> CONTACTS_ORGANIZATION_1 = new EnumMap<>(ContactType.class);
    private static final Address ADDRESS_1_ORGANIZATION_1 = new Address("Chadan", "7565 Golden Leaf Terrace", 18.9494246, 72.8305922);
    private static final Address ADDRESS_2_ORGANIZATION_1 = new Address("Cincinnati", "2456 Buena Vista Road", 39.0745039, -84.33211);

    public static final Long ID_NOT_FOUND = 200L;

    static {
        CONTACTS_ORGANIZATION_1.put(ContactType.URL, "https://sfgate.com");
    }

    public static final Organization ORGANIZATION_1 = new Organization(ORGANIZATION_1_ID,
            "Tamil Nadu Dr. M.G.R. Medical University",
            "pretium iaculis justo in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat",
            Set.of(OrganizationCategory.BUSINESS, OrganizationCategory.PARENT, OrganizationCategory.TEACHER),
            CONTACTS_ORGANIZATION_1,
            List.of(ADDRESS_1_ORGANIZATION_1, ADDRESS_2_ORGANIZATION_1)
    );

    public static Organization getNewOrganization() {
        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        contacts.put(ContactType.URL, "https://new.org");
        Organization newOrganization = new Organization("New Organization", "New Description", List.of(OrganizationCategory.BUSINESS), contacts);
        Address address = new Address("New City", "new address", 1.0, 2.0, newOrganization);
        newOrganization.addAddress(address);
        return newOrganization;
    }
}
