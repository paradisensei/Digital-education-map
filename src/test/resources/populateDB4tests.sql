DELETE
FROM address
WHERE id >= 1000;

DELETE
FROM contact
WHERE organization_id >= 1000;

DELETE
FROM organization_category
WHERE organization_id >= 1000;

DELETE
FROM organization
WHERE id >= 1000;

ALTER SEQUENCE contact_seq RESTART WITH 1000;
ALTER SEQUENCE address_seq RESTART WITH 1000;
ALTER SEQUENCE organization_seq RESTART WITH 1000;

--for delete
insert into organization (name, description) values ('test delete', 'test delete');

