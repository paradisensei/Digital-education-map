# Гайд по разработке

Весь рабочий процесс организован в [GitHub репозитории](https://github.com/paradisensei/Digital-education-map).  

### Структура

Для постановки и описания задач используется вкладка [Issues](https://github.com/paradisensei/Digital-education-map/issues)  
Для отслеживания состояния проекта и статуса всех задач используется [канбан доска](https://github.com/paradisensei/Digital-education-map/projects/1)  
Для сбора всей релевантной информации по проекту используется [вики](https://github.com/paradisensei/Digital-education-map/wiki)

### С чего начать?

###### Подготовка окружения

1. Сделать свой собственный форк кода, в котором вы будете работать над задачами

###### Работа над задачей (issue)

1. Выбрать issue, над которым хотите поработать (в некоторых задачах может быть нужен небольшой ресерч, свой вклад могут вносить сразу несколько участников)
2. В своем форке сделать ветку под задачу. Название ветки должно кратко описывать задачу (например, add-google-maps-api)
3. Сообщения для коммитов должны прозрачно и лаконично описывать суть изменений (об этом хорошо написано [здесь](https://chris.beams.io/posts/git-commit/))
4. Если в рамках задачи были изменены параметры запуска или какие-то properties, нужно обновить [README.md](https://github.com/paradisensei/Digital-education-map/blob/master/README.md), чтобы в репозитории всегда была актуальная информация по работе с проектом
5. Когда закончили с задачей, можно открывать Pull Request вашей ветки в ветку master оригинального репозитория (если это ваш первый Pull Request, [здесь](https://egghead.io/courses/how-to-contribute-to-an-open-source-project-on-github) можно почитать об этом подробнее)
6. По описанию Pull Request-а должно быть понятно, в чем суть изменений. Также оно должно содержать ссылку на issue (например, Решает #{номер issue}) 

###### Review

1. Peer Review приветствуется, то есть можно и нужно ревьюить работу своих товарищей
2. Для начала maintainer-ом (тот, кто окончательно принимает Pull Request-ы) будет только [автор](https://github.com/paradisensei). В процессе самые активные участники также будут становиться maintainer-ами
3. Если в рамках задачи вы сделали много коммитов в своей ветке, то после аппрува Pull Request-а, пожалуйста, причешите историю коммитов, чтобы в master попадала прозрачная и красивая история изменений 