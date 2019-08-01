<#include "../login-template.ftl"/>

<#macro content>
    <div class="jumbotron text-center">
        <form class="form-signin" action="/admin/login" method="post">
            <h1>Log in</h1>
            <div class="mb-3">
                <label for="username" class="sr-only">Email address</label>
                <input type="email" name="username" id="username" class="form-control" placeholder="Email address"
                       required
                       autofocus value="admin@gmail.com"/>
                <label for="password" class="sr-only">Password</label>
                <input type="password" name="password" id="password" class="form-control" placeholder="Password"
                       required
                       value="admin"/>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
        <a href="/">На главную</a>
    </div>
</#macro>

<#if errorMessage??>
    <div style="color:red;font-style:italic;">
        ${errorMessage}
    </div>
</#if>

<@main title="Вход"/>
