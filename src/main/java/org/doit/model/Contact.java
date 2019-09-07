package org.doit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Access(AccessType.FIELD)
public class Contact {

    @Id
    @SequenceGenerator(name = "contact_gen", sequenceName = "contact_seq", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_gen")
    private long id;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Organization organization;

    public Contact(ContactType type, String value) {
        this.type = type;
        this.value = value;
    }
}
