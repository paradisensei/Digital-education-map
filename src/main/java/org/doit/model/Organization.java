package org.doit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Access(AccessType.FIELD)
public class Organization {

    @Id
    @SequenceGenerator(name = "organization_gen", sequenceName = "organization_seq", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_gen")
    private long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "organization_category", joinColumns = @JoinColumn(name = "organization_id"))
    @Column(name = "category")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<OrganizationCategory> categories;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "contact", joinColumns = @JoinColumn(name = "organization_id"))
    @MapKeyColumn(name = "type")
    @MapKeyClass(ContactType.class)
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "value")
    private Map<ContactType, String> contacts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization", orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    public Organization(String name, String description) {
        this(name, description, List.of(), Map.of());
    }

    public Organization(String name, String description, Collection<OrganizationCategory> categories, Map<ContactType, String> contacts) {
        this.name = name;
        this.description = description;
        setCategories(categories);
        setContacts(contacts);
    }

    public Organization(Organization o) {
        this.name = o.getName();
        this.description = o.getDescription();
        this.categories = o.getCategories();
        this.contacts = o.getContacts();
        this.addresses = o .getAddresses();
    }

    private void setCategories(Collection<OrganizationCategory> categories) {
        this.categories = CollectionUtils.isEmpty(categories) ? EnumSet.noneOf(OrganizationCategory.class) : EnumSet.copyOf(categories);
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;

        return getId() == ((Organization) o).getId();

    }
}
