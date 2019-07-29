<#include "../main-template.ftl"/>

<#macro content>
    <div id="map" style="width: 100vh; height: 100vh;"></div>
</#macro>

<@main title="Карта цифрового образования"
       scripts=["https://api-maps.yandex.ru/2.1/?apikey=<${yandexKey}>&lang=ru_RU", "/resources/js/main.js"]
       styles=["/resources/css/main.css"]
/>
