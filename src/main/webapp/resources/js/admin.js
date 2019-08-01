function editOrganization(id) {
    $.getJSON("/rest/organization/" + id, function (organization) {
        $('#organizationModalLabel').text("Редактирование организации");
        $('#organizationId').val(organization.id);
        $('#organizationName').val(organization.name);
        $('#organizationDescription').text(organization.description);
        $('#saveButton').text("Сохранить");
        $('#organizationModal').modal('show');
    });
}

function clearForm() {
    $('#organizationModalLabel').text("Добавление организации");
    $('#organizationId').val("0");
    $('#organizationName').val("");
    $('#organizationDescription').text("");
    $('#saveButton').text("Добавить")
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
