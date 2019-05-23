package org.doit.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Access(AccessType.FIELD)
public class Address {

    @Id
    @SequenceGenerator(name = "address_gen", sequenceName = "address_seq", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_gen")
    protected Long id;

    private String city;

    private String address;

    private Double latitude;

    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public Address() {
    }

    public Address(String city, String address, Double latitude, Double longitude) {
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Address(String city, String address, Double latitude, Double longitude, Organization organization) {
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("city='" + city + "'")
                .add("address='" + address + "'")
                .add("latitude=" + latitude)
                .add("longitude=" + longitude)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
