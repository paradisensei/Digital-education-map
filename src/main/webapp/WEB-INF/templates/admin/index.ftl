<#include "admin-template.ftl"/>

<#macro content>
    <#if organizations??>
        <div class="p-2 my-5">
            <h1>Организации
                <button type="button" class="btn btn-secondary" onclick="newOrganization()" data-toggle="modal"
                        data-target="#organizationModal">Добавить
                </button>
            </h1>

            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <tr>
                        <th>ID</th>
                        <th>Наименование</th>
                        <th>Описание</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <#list organizations as organization>
                        <tr>
                            <td>${organization.getId()}</td>
                            <td>${organization.getName()}</td>
                            <td>${organization.getDescription()}</td>
                            <td><a href="#" onclick="editOrganization(${organization.getId()?c})"><i
                                            class="fas fa-edit"></i></a></td>
                            <td><a href="#" onclick="deleteOrganization(${organization.getId()?c})"><i
                                            class="fas fa-trash-alt"></i></a></td>
                        </tr>
                    </#list>
                </table>
            </div>
        </div>

        <div class="modal fade " id="organizationModal" tabindex="-1" role="dialog"
             data-aria-labelledby="organizationModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="organizationModalLabel">Добавление адресов</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="form" method="post">
                            <input type="hidden" name="id" id="organizationId" value="">
                            <div class="form-group">
                                <label for="organizationName" class="col-form-label">Наименование организации:</label>
                                <input type="text" class="form-control" name="name" id="organizationName" value="">
                            </div>
                            <div class="form-group">
                                <label for="organizationDescription" class="col-form-label">Описание:</label>
                                <textarea class="form-control" rows="5" name="description"
                                          id="organizationDescription"></textarea>
                            </div>

                            <div class="form-group">
                                <#list categories as category>
                                    <label for="${category.name()}"
                                           class="col-form-label">${category.getTitle()}
                                        <input type="checkbox" id="${category.name()}" name="categories"
                                               class="form-control" value="${category.name()}">
                                    </label>
                                </#list>
                            </div>

                            <h4>Котакты:</h4>
                            <div class="form-group form-inline" id="contacts">
                            </div>
                            <button id="addContactButton" type="button" class="btn btn-secondary" onclick="">Добавить
                            </button>

                            <h4>Адреса:</h4>
                            <div class="form-group form-inline" id="addresses">
                            </div>
                            <button id="addAddressButton" type="button" class="btn btn-secondary" onclick="">Добавить
                            </button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                        <button id="saveButton" onclick="saveOrganization()" type="button"
                                class="btn btn-primary"></button>
                    </div>
                </div>
            </div>
        </div>

    </#if>
</#macro>

<@main title="Организации"
scripts=["/resources/js/admin.js"]
styles=["/resources/css/main.css", "/webjars/font-awesome/css/fontawesome.css", "/webjars/font-awesome/css/solid.css"]
/>