# Карта цифрового образования России

Онлайн-платформа, отражающая актуальные сведения обо всем цифровом образовании России в виде карты с отмеченными на ней образовательными организациями.
> Проект разрабатывается силами сообщества [образовательного YouTube канала](https://www.youtube.com/channel/UCwMl9L2VNAR0upPrkhAo_Ig)  
> [Здесь](https://paradise152771.typeform.com/to/uYkrq6) немного подробнее о том, как появилась эта идея  
> Вся коммуникация по проекту в [slack](https://join.slack.com/t/doit-dt59172/shared_invite/enQtNjIwMjIxMDEyMDA1LTBiN2UyZjkyZDQyZmU3M2U1ZWJhODhkMmZhYWU2MGFjMDQ5NGU0NWI4ZGNjNzE0MWQ2ZTUyMWIzMjEzYjAxZTA)

### Инструкции по запуску

1. Создать persistence.properties в папке [resources](https://github.com/paradisensei/Digital-education-map/tree/master/src/main/resources)
2. Добавить Yandex MAP key в файл application.properties (Инструкция по получению ключа [тут](https://tech.yandex.ru/maps/doc/jsapi/2.1/quick-start/index-docpage/))
3. Развернуть собранный war файл в любимом веб-сервере

### Инструкция по развертыванию БД

1. Настроить параметры driver, url, username, password в src/main/resources/liquibase.properties для БД.
2. выполнить `mvn liquibase:update -P test-with-data` для развертывания с тестовыми данными, либо `mvn liquibase:update -P test-without-data` развертывания только структуры.

### Запуск тестов

Запуск всех тестов:  `mvn test -P test-with-data`


### Contributing

Прежде чем начать, пожалуйста, прочитайте гайд по разработке [CONTRIBUTING.md](https://github.com/paradisensei/Digital-education-map/blob/master/.github/CONTRIBUTING.md). Он далеко не окончательный, будем дорабатывать в процессе.

### License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/paradisensei/Digital-education-map/blob/master/LICENSE) file for details
