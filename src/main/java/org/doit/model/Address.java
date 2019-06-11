package org.doit.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Access(AccessType.FIELD)
public class Address {

    @Id
    @SequenceGenerator(name = "address_gen", sequenceName = "address_seq", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_gen")
    private Long id;

    private String city;

    private String address;

    private Double latitude;

    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

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
}
