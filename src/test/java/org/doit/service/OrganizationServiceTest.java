package org.doit.service;

import org.doit.config.CoreConfig;
import org.doit.config.PersistenceConfig;
import org.doit.model.Organization;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.doit.OrganizationTestData.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CoreConfig.class, PersistenceConfig.class})
@Sql(scripts = "classpath:populateDB4tests.sql", config = @SqlConfig(encoding = "UTF-8"))
public class OrganizationServiceTest {

    @Autowired
    protected OrganizationService service;

    @Test
    void create() {
        Organization newOrganization = getNewOrganization();
        Organization actual = service.create(newOrganization);
        newOrganization.setId(actual.getId());
        assertEquals(newOrganization, actual);
    }

    @Test
    void createDuplicateName() {
        Organization duplicateOrganization = new Organization(ORGANIZATION_1.getName(), ORGANIZATION_1.getDescription(), ORGANIZATION_1.getCategories(), ORGANIZATION_1.getContacts());
        assertThrows(DataAccessException.class, () -> service.create(duplicateOrganization));
    }

    @Test
    void delete() {
        int countBefore = service.getAll().size() - 1;
        service.delete(ORGANIZATION_DELETE_ID);
        assertEquals(countBefore, service.getAll().size());
        assertNull(service.get(ORGANIZATION_DELETE_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> service.delete(ID_NOT_FOUND));
    }

    @Test
    void get() {
        Organization actual = service.get(ORGANIZATION_1_ID);
        assertEquals(ORGANIZATION_1, actual);
    }

    @Test
    void getNotFound() {
        assertNull(service.get(ID_NOT_FOUND));
    }

    @Test
    void getAll() {
        assertEquals(100, service.getAll().size());
    }
}
