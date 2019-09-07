package org.doit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Access(AccessType.FIELD)
public class Address {

    @Id
    @SequenceGenerator(name = "address_gen", sequenceName = "address_seq", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_gen")
    private long id;

    private String city;

    private String address;

    private Double latitude;

    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Organization organization;

    public Address(String city, String address, Double latitude, Double longitude) {
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
