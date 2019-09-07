const organizationModal = $("#organizationModal");

function newOrganization() {
    $('#organizationModalLabel').text("Добавление организации");
    $('#organizationId').val("0");
    $('#organizationName').val("");
    $('#organizationDescription').text("");
    $('input[name=categories]').prop("checked", false);
    $('#contacts').text("");
    $('#addresses').text("");
    $('#saveButton').text("Добавить");
    $('#addContactButton').attr("onclick", "addContact(" + 0 + ", " + 0 + ")");
    $('#addAddressButton').attr("onclick", "addAddress(" + 0 + ", " + 0 + ")");
}

function editOrganization(id) {
    function setFields(organization) {
        $('#organizationModalLabel').text("Редактирование организации");
        $('#organizationId').val(organization.id);
        $('#organizationName').val(organization.name);
        $('#organizationDescription').text(organization.description);
        $('#saveButton').text("Сохранить");

        let nextContact = $('#contacts').length - 1;
        $('#addContactButton').attr("onclick", "addContact(" + nextContact + ", " + organization.id + ")");
        let nextAddress = $('#addresses').length - 1;
        $('#addAddressButton').attr("onclick", "addAddress(" + nextAddress + ", " + organization.id + ")");

    }

    $.getJSON("/rest/organization/" + id, function (organization) {
        setFields(organization);
        checkCategories(organization.categories);
        addContacts(organization.contacts);
        addAddresses(organization.addresses);
        organizationModal.modal('show');
    });
}

function checkCategories(categories) {
    $.each(categories, function (key, val) {
        let id = '#' + val;
        $(id).prop("checked", true);
    });
}

function addContacts(contacts) {
    const contactsId = $('#contacts');
    contactsId.text("");
    $.each(contacts, function (key, contact) {
        contactsId.append(
            "<div id='contact" + key + "'>" +
            getSelectContactTypes(key, contact['type']) +
            "<input type='hidden' name='contacts[" + key + "].id' value='" + contact['id'] + "'>" +
            // "<input type='hidden' name='contacts[" + key + "].organization' value='" + contact['organization'] + "'>" +
            "<input type='text' class='form-control' name='contacts[" + key + "].value' value='" + contact['value'] + "'>" +
            "<button type='button' name='deleteBtn' class='btn btn-warning' onclick='deleteContact(" + key + ")'>удалить</button></div>"
        );
        let c = key + 1;
        $('#addContactButton').attr("onclick", "addContact(" + c + ", " + contact['organization'] + ")");
    });
}

function getSelectContactTypes(key, type) {
    //TODO не хорошо, что типы контактов на фронте статичны
    let contactTypes = ['PHONE', 'EMAIL', 'URL'];
    let result = "<select name='contacts[" + key + "].type' class='form-control'>";
    $.each(contactTypes, function (key, contactType) {
        let selected = (contactType === type) ? "selected" : "";
        result += "<option value='" + contactType + "'" + selected + ">" + contactType + "</option>"
    });
    result += "</select>";
    return result;
}

function deleteContact(id) {
    let key = 0;
    $('#contact' + id).remove();
    $('#contacts').children().each(function () {
        this.id = 'contact' + key;
        $(this).children().each(function () {
            if (this.name.includes("type")) {
                this.name = "contacts[" + key + "].type";
            }
            if (this.name.includes("id")) {
                this.name = "contacts[" + key + "].id";
            }
            if (this.name.includes("organization")) {
                this.name = "contacts[" + key + "].organization";
                let nextKey = key + 1;
                $('#addContactButton').attr("onclick", "addContact(" + nextKey + ", " + this.value + ")");
            }
            if (this.name.includes("value")) {
                this.name = "contacts[" + key + "].value";
            }
            if (this.name.includes("Btn")) {
                console.log(this);
                let onclick = "deleteContact(" + key + ")";
                $(this).attr('onclick', onclick);
            }
        });
        key++;
    });
}

function addContact(key, organization) {
    const contactsId = $('#contacts');
    contactsId.append("<div class='form-group'>" +
        "<div id='contact" + key + "'>" +
        getSelectContactTypes(key) +
        "<input type='hidden' name='contacts[" + key + "].id' value='0'>" +
        "<input type='text' class='form-control' name='contacts[" + key + "].value' value=''>" +
        "<button type='button' name='deleteBtn' class='btn btn-warning' onclick='deleteContact(" + key + ")'>удалить</button></div>"
    );
    key++;
    $('#addContactButton').attr("onclick", "addContact(" + key + ", " + organization + ")");
}


function addAddresses(addresses) {
    const addressesId = $('#addresses');
    addressesId.text("");
    $.each(addresses, function (key, address) {
        addressesId.append(
            "<div id='address" + key + "'>" +
            "<input type='hidden' name='addresses[" + key + "].id' value='" + address['id'] + "'>" +
            "<label>city</label>" +
            "<input type='text' class='form-control' name='addresses[" + key + "].city' value='" + address['city'] + "'>" +
            "<label>address</label>" +
            "<input type='text' class='form-control' name='addresses[" + key + "].address' value='" + address['address'] + "'>" +
            "<label>latitude</label>" +
            "<input type='text' class='form-control' name='addresses[" + key + "].latitude' value='" + address['latitude'] + "'>" +
            "<label>lognitude</label>" +
            "<input type='text' class='form-control' name='addresses[" + key + "].longitude' value='" + address['longitude'] + "'>" +
            "<button type='button' name='deleteBtn' class='btn btn-warning' onclick='deleteAddress(" + key + ")'>удалить</button></div>"
        );
        let c = key + 1;
        $('#addAddressButton').attr("onclick", "addAddress(" + c + ", " + address['organization'] + ")");
    });
}

function deleteAddress(id) {
    let key = 0;
    $('#address' + id).remove();
    $('#addresses').children().each(function () {
        this.id = 'address' + key;
        $(this).children().each(function () {
            if ($(this).is("input") && this.name.includes("id")) {
                this.name = "addresses[" + key + "].id";
            }
            if ($(this).is("input") && this.name.includes("city")) {
                this.name = "addresses[" + key + "].city";
            }
            let key1 = key+1 ;
            if ($(this).is("input") && this.name === ("addresses[" + key1 + "].address")) {
                this.name = "addresses[" + key + "].address";
            }
            if ($(this).is("input") && this.name.includes("latitude")) {
                this.name = "addresses[" + key + "].latitude";
            }
            if ($(this).is("input") && this.name.includes("longitude")) {
                this.name = "addresses[" + key + "].longitude";
            }
            if ($(this).is("input") && this.name.includes("organization")) {
                this.name = "addresses[" + key + "].organization";
                let nextKey = key + 1;
                $('#addAddressButton').attr("onclick", "addContact(" + nextKey + ", " + this.value + ")");
            }
            if ($(this).is("button") && this.name.includes("Btn")) {
                let onclick = "deleteAddress(" + key + ")";
                $(this).attr('onclick', onclick);
            }
        });
        key++;
    });
}

function addAddress(key, organization) {
    const addressesId = $('#addresses');
    addressesId.append("<div class='form-group'>" +
        "<div id='address" + key + "'>" +
        "<input type='hidden' name='addresses[" + key + "].id' value='0'>" +
        "<label>city</label>" +
        "<input type='text' class='form-control' name='addresses[" + key + "].city' value=''>" +
        "<label>address</label>" +
        "<input type='text' class='form-control' name='addresses[" + key + "].address' value=''>" +
        "<label>latitude</label>" +
        "<input type='text' class='form-control' name='addresses[" + key + "].latitude' value=''>" +
        "<label>longitude</label>" +
        "<input type='text' class='form-control' name='addresses[" + key + "].longitude' value=''>" +
        // "<input type='hidden' name='addresses[" + key + "].organization' value='" + organization + "'>" +
        "<button type='button' name='deleteBtn' class='btn btn-warning' onclick='deleteAddress(" + key + ")'>удалить</button></div>"
    );
    key++;
    $('#addAddressButton').attr("onclick", "addAddress(" + key + ", " + organization + ")");
}


function deleteOrganization(id) {
    if (confirm("Действительно удалить?")) {
        $.ajax({
            url: "/admin/organizations/" + id,
            type: "DELETE"
        }).done(function () {
            location.reload();
        });
    }
}

function saveOrganization() {
    let data = $('#form').serialize();
    $.ajax({
        type: "POST",
        url: "/admin/organizations",
        data: data,
    }).done(function () {
        organizationModal.modal("hide");
        location.reload();
    });
}