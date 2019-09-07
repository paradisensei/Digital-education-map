<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<#macro main title>
    <!DOCTYPE html>
    <html lang="ru">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="/resources/favicon.ico"/>
        <link rel="stylesheet" href="/resources/css/main.css">
        <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">

        <title>${title}</title>
    </head>

    <body class="bg-dark h-100">
        <div class="container h-100">
            <div class="row h-100 justify-content-center align-items-center">
                <@content/>
            </div>
        </div>
    </body>
    </html>
</#macro>