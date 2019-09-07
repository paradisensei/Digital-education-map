package org.doit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "organization_id")
    @Fetch(FetchMode.SUBSELECT)
    private List<Contact> contacts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "organization_id")
    @Fetch(FetchMode.SUBSELECT)
    private List<Address> addresses = new ArrayList<>();

    public Organization(String name, String description, Set<OrganizationCategory> categories) {
        this.name = name;
        this.description = description;
        this.categories = categories;
    }

    public Organization(Organization o) {
        this.name = o.getName();
        this.description = o.getDescription();
        this.categories = o.getCategories();
        this.contacts = o.getContacts();
        this.addresses = o.getAddresses();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;

        return getId() == ((Organization) o).getId();
    }
}
