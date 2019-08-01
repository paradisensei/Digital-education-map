<#include "../main-template.ftl"/>

<#macro content>
    <div class="container-fluid m-0 p-0 h-100">
        <div id="map" class="mt-5 w-100 h-100"></div>
    </div>
</#macro>

<@main title="Карта цифрового образования"
       scripts=["https://api-maps.yandex.ru/2.1/?apikey=<${yandexKey}>&lang=ru_RU", "/resources/js/main.js"]
       styles=["/resources/css/main.css"]
/>
