# Карта цифрового образования России

Онлайн-платформа, отражающая актуальные сведения обо всем цифровом образовании России в виде карты с отмеченными на ней образовательными организациями.
> Проект разрабатывается силами сообщества [образовательного YouTube канала](https://www.youtube.com/channel/UCwMl9L2VNAR0upPrkhAo_Ig)  
> [Здесь](https://paradise152771.typeform.com/to/uYkrq6) немного подробнее о том, как появилась эта идея

### Инструкции по запуску

1. Развернуть собранный war файл в любимом веб-сервере

### Инструкция по развертыванию БД

1. Настроить параметры driver, url, username, password в src/main/resources/liquibase.properties для БД.
2. выполнить `mvn liquibase:update -P test-with-data` для развертывания с тестовыми данными, либо `mvn liquibase:update -P test-without-data` развертывания только структуры.

### Contributing

Прежде чем начать, пожалуйста, прочитайте гайд по разработке [CONTRIBUTING.md](https://github.com/paradisensei/Digital-education-map/blob/master/.github/CONTRIBUTING.md). Он далеко не окончательный, будем дорабатывать в процессе.

### License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/paradisensei/Digital-education-map/blob/master/LICENSE) file for details
