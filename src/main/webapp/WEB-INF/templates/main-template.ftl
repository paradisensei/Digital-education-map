<#macro main title>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://api-maps.yandex.ru/2.1/?apikey=<${yandexKey}>&lang=ru_RU"
                type="text/javascript">
        </script>
        <script src="resources/js/main.js" type="text/javascript"></script>

        <title>${title}</title>
    </head>

    <body>
        <@content/>
    </body>
</#macro>