ymaps.ready(init);

let objectManager;

function init() {
    const myMap = new ymaps.Map(
        "map",
        {
            center: [63, 85],
            zoom: 4,
            controls: ['zoomControl'],
        },
        {
            minZoom: 3,
        }
    );

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
            const { name, description, categories } = o
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
                        'hintContent': `<div class='balloonHint'>${name}</div>`,
                        'categories': categories,
                        'city': a.city
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

// filtering helper functions
function cityFilter(city) {
    objectManager.setFilter(object => object.properties.city.toLowerCase().startsWith(city.toLowerCase()));
}

function categoryFilter(category) {
    objectManager.setFilter(object => object.properties.categories.includes(category));
}

function cityAndCategoryFilter(city, category) {
    objectManager.setFilter(object =>
        object.properties.categories.includes(category)
        &&
        object.properties.city.toLowerCase().startsWith(city.toLowerCase())
    );
}
// --- filtering helper functions ---

// UI event handlers
function onCategoryChoice(category) {
    const city = $('#city').val()
    if (category === 'ALL') {
        city === '' ? objectManager.setFilter('true') : cityFilter(city);
    } else if (city !== '') {
        cityAndCategoryFilter(city, category)
    } else {
        categoryFilter(category)
    }
}

function onCityTyping(city) {
    const category = $('#category').val()
    if (city === '') {
        category === 'ALL' ? objectManager.setFilter('true') : categoryFilter(category)
    } else if (category !== 'ALL') {
        cityAndCategoryFilter(city, category)
    } else {
        cityFilter(city)
    }
}
// --- UI event handlers ---