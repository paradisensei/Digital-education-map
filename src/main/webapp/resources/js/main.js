ymaps.ready(init);

function init() {
    var myMap = new ymaps.Map("map", {
            center: [66.25, 94.15],
            zoom: 4,
            controls: ['zoomControl'],
        },
        {
            minZoom: 3,
        }),

        objectManager = new ymaps.ObjectManager({
            // Чтобы метки начали кластеризоваться, выставляем опцию.
            clusterize: true,
            // ObjectManager принимает те же опции, что и кластеризатор.
            gridSize: 32,
            clusterDisableClickZoom: true,
        });

    // Чтобы задать опции одиночным объектам и кластерам,
    // обратимся к дочерним коллекциям ObjectManager.
    objectManager.objects.options.set('preset', 'islands#blackLeisureIcon');
    objectManager.clusters.options.set('preset', 'islands#blackClusterIcons');
    myMap.geoObjects.add(objectManager);

    $.ajax({
        url: "/rest/organizations"
    }).done(function (data) {
        objectManager.add(data);
    });
}