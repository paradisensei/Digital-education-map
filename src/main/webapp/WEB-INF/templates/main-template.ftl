<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#macro main title>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="/resources/css/main.css">

        <script src="https://api-maps.yandex.ru/2.1/?apikey=<${yandexKey}>&lang=ru_RU"
                type="text/javascript">
        </script>
        <script src="/webjars/jquery/jquery.min.js"></script>
        <script src="/resources/js/main.js" type="text/javascript"></script>

        <title>${title}</title>
    </head>

    <body>
    <nav>
        <a href="/">home</a>
        <a href="/admin">admin</a>
        <@security.authorize access="isAuthenticated()">
            <a href="/logout">logout</a>
        </@security.authorize>
    </nav>

    <@content/>
    </body>
    </html>
</#macro>