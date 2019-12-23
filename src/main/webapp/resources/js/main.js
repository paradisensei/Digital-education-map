ymaps.ready(init);

function init() {
    const myMap = new ymaps.Map("map", {
            center: [63, 85],
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
    }).done(function (orgs) {
        // array of geo objects
        const features = [];
        orgs.forEach(o => {
            const { name, description } = o
            // NOTE should support multiple contacts post-MVP
            const contact = o.contacts[0].value
            o.addresses.forEach(a => {
                features.push({
                    id: a.id,
                    type: 'Feature',
                    geometry: {
                        type: 'Point',
                        coordinates: [a.latitude, a.longitude]
                    },
                    properties: {
                        'balloonContentHeader': `<div class='balloonHeader'>${name}</div>`,
                        'balloonContentBody':
                            `<div class='balloonBody'>
                                <div class='balloonAddress'>${a.city} ${a.address}</div>
                                <div class='balloonContacts'>
                                    <div><a href='${contact}'>${contact}</a></div>
                                </div>
                            </div>`,
                        'hintContent': `<div class='balloonHint'>${name}</div>`
                    }
                })
            })
        })
        objectManager.add({
            type: 'FeatureCollection',
            features
        });
    });
}