package org.doit.repository;

import org.doit.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @Query("SELECT DISTINCT o FROM Organization o JOIN FETCH o.addresses JOIN FETCH o.contacts")
    List<Organization> findAllWithAddressesAndContacts();
}
