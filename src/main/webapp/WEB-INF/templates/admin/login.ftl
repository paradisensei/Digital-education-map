<#include "admin-template.ftl"/>

<#macro content>
    <h1>Log in</h1>
    <form role="form" action="/admin/login" method="post">
        <div>
            <label for="username">Email address</label>
            <input type="email" name="username" id="username" required autofocus value="admin@gmail.com"/>
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" name="password" id="password" required value="admin"/>
        </div>
        <button type="submit">Sign in</button>
    </form>
</#macro>

<#if errorMessage??>
    <div style="color:red;font-style:italic;">
        ${errorMessage}
    </div>
</#if>

<@main title="Login"/>
