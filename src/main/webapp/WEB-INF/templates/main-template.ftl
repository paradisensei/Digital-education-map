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
                    Digital Education Map
                </a>
                <ul class="navbar-nav mr-auto">
                    <@security.authorize access="isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link" href="/admin/">Организации</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/logout">Выход</a>
                        </li>
                    </@security.authorize>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <label for="category" class="navbar-brand">Категория</label>
                    <select class="form-control mr-sm-2" id="category" type="select"
                            onchange="onCategoryChoice(this.value)">
                        <option value="ALL">Все</option>
                        <#list categories as category>
                            <option value="${category}">${category.title}</option>
                        </#list>
                    </select>
                    <label for="city" class="navbar-brand">Город</label>
                    <input class="form-control mr-sm-2" id="city" type="text" placeholder="Город"
                           oninput="onCityTyping(this.value)">
                </form>
            </nav>
        </header>
        <main role="main" class="h-100">
                <@content/>
        </main>

        <footer class="footer fixed-bottom mt-auto py-1 bg-dark">
            <div class="container-fluid">
                <span class="text-muted">&copy digital-education-map</span>
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