package org.doit;

import org.doit.model.Address;
import org.doit.model.Contact;
import org.doit.model.ContactType;
import org.doit.model.Organization;
import org.doit.model.OrganizationCategory;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrganizationTestData {
    public static final long ORGANIZATION_1_ID = 14L;
    public static final long ORGANIZATION_DELETE_ID = 1000L;

    private static final List<Contact> CONTACTS_ORGANIZATION_1 = new ArrayList<>();

    private static final Address ADDRESS_1_ORGANIZATION_1;
    private static final Address ADDRESS_2_ORGANIZATION_1;

    public static final long ID_NOT_FOUND = 200L;

    static {
        CONTACTS_ORGANIZATION_1.add(new Contact(ContactType.URL, "https://sfgate.com"));
        ADDRESS_1_ORGANIZATION_1 = new Address("Chadan", "7565 Golden Leaf Terrace", 18.9494246, 72.8305922);
        ADDRESS_2_ORGANIZATION_1 = new Address("Cincinnati", "2456 Buena Vista Road", 39.0745039, -84.33211);
    }

    public static final Organization ORGANIZATION_1 = new Organization(ORGANIZATION_1_ID,
            "Tamil Nadu Dr. M.G.R. Medical University",
            "pretium iaculis justo in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat",
            Set.of(OrganizationCategory.BUSINESS, OrganizationCategory.PARENT, OrganizationCategory.TEACHER),
            CONTACTS_ORGANIZATION_1,
            List.of(ADDRESS_1_ORGANIZATION_1, ADDRESS_2_ORGANIZATION_1)
    );

    public static Organization getNewOrganization() {
        return new Organization("New Organization", "New Description", Set.of(OrganizationCategory.BUSINESS));
    }
}
