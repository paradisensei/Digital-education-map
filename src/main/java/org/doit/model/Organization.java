package org.doit.model;

import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

@Entity
@Access(AccessType.FIELD)
public class Organization{

    @Id
    @SequenceGenerator(name = "organization_gen", sequenceName = "organization_seq", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_gen")
    protected Long id;

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

    public Organization() {
    }

    public Organization(String name, String description, Collection<OrganizationCategory> categories, Map<ContactType, String> contacts) {
        this.name = name;
        this.description = description;
        setCategories(categories);
        setContacts(contacts);
    }

    public Organization(Long id, String name, String description, Collection<OrganizationCategory> categories, Map<ContactType, String> contacts) {
        this.id = id;
        this.name = name;
        this.description = description;
        setCategories(categories);
        setContacts(contacts);
    }

    public Organization(String name, String description, Collection<OrganizationCategory> categories, Map<ContactType, String> contacts, List<Address> addresses) {
        this.name = name;
        this.description = description;
        setCategories(categories);
        setContacts(contacts);
        this.addresses = addresses;
    }

    public Organization(Long id, String name, String description, Collection<OrganizationCategory> categories, Map<ContactType, String> contacts, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.description = description;
        setCategories(categories);
        setContacts(contacts);
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<OrganizationCategory> getCategories() {
        return categories;
    }

    private void setCategories(Collection<OrganizationCategory> categories) {
        this.categories = CollectionUtils.isEmpty(categories) ? EnumSet.noneOf(OrganizationCategory.class) : EnumSet.copyOf(categories);
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    private void setContacts(Map<ContactType, String> contacts) {
        this.contacts = contacts;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Organization.class.getSimpleName() + "[", "]")
                .add("id=" + id + "'")
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("categories=" + categories)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
