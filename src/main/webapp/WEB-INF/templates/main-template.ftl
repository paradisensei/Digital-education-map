<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<#macro main title scripts=[] styles=[]>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="/resources/favicon.ico"/>

        <title>${title}</title>

        <#list styles as style>
            <link rel="stylesheet" type="text/css" href="${style}">
        </#list>
    </head>

    <body>
        <@content/>

        <!-- jQuery -->
        <script type="application/javascript" src="/webjars/jquery/jquery.min.js"></script>

        <#list scripts as script>
            <script type="application/javascript" src="${script}"></script>
        </#list>
    </body>
    </html>
</#macro>