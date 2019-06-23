<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#macro main title>
    <!DOCTYPE html>
    <html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${title}</title>
</head>

<body>
<nav>
    <a href="/">home</a>
    <a href="/admin">admin</a>
    <@security.authorize access="isAuthenticated()">
        <a href="/admin/organizations">organizations</a>
        <a href="/logout">logout</a>
    </@security.authorize>
</nav>
<@content/>

</body>
</#macro>