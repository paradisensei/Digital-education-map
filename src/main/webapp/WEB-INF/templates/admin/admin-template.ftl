<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<#macro main title scripts=[] styles=[]>
    <!DOCTYPE html>
    <html lang="ru">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="/resources/favicon.ico"/>

        <title>${title}</title>

        <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css">

        <#list styles as style>
            <link rel="stylesheet" type="text/css" href="${style}">
        </#list>
    </head>

    <body class="h-100">
    <header>
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <a class="navbar-brand" href="/">
                <img src="/resources/favicon.ico" width="30" height="30" class="d-inline-block align-top" alt="">
                Карта цифрового образования
            </a>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Карта</a>
                </li>
                <@security.authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/logout">Выход</a>
                    </li>
                </@security.authorize>
            </ul>
        </nav>
    </header>
    <main role="main" class="h-100">
        <@content/>
    </main>

    <footer class="footer fixed-bottom mt-auto py-1 bg-dark">
        <div class="container-fluid">
            <span class="text-muted">&copy Карты цифрового образования</span>
        </div>
    </footer>

    <!-- jQuery -->
    <script type="application/javascript" src="/webjars/jquery/jquery.min.js"></script>

    <!-- Bootsrap -->
    <script type="application/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>

    <#list scripts as script>
        <script type="application/javascript" src="${script}"></script>
    </#list>
    </body>
    </html>
</#macro>