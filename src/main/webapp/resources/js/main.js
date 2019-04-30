ymaps.ready(init);
function init(){
    var myMap = new ymaps.Map("map", {
        center: [59.94, 30.32],
        zoom: 7
    });

    var placemark = new ymaps.Placemark([59.97, 30.31], {
        hintContent: '<div class "map__hint">ул. Литераторов. д.19</div>',
        ballonContent: 'Это балун'
    });

    myMap.geoObjects.add(placemark);
}