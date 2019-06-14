<#include "admin-template.ftl"/>
<#macro content>
    <form id="form" method="post" action="/admin/organizations">
        <label for="name">name</label><input type="text" name="name" id="name" value=""/>
        <label for="description">desc</label><input type="text" name="description" id="description"/>
        <button type="submit">save</button>
    </form>
    <#if organizations??>
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>descr</th>
                <th>categories</th>
            </tr>
            <#list organizations as organization>
                <tr>
                    <td>${organization.getId()}</td>
                    <td>${organization.getName()}</td>
                    <td>${organization.getDescription()}</td>
                    <td>
                        <#list organization.getCategories() as category>
                            ${category}&nbsp;
                        </#list>
                    </td>
                </tr>
            </#list>
        </table>
    </#if>
</#macro>

<@main title="Admin"/>
