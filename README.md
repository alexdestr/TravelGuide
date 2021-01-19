# TravelGuide
RU:
Юзернейм бота: Trav_Guide_bot
Telegram: @Trav_Guide_bot
Токен бота: 1595934785:AAG4KHp2_hm0duu4DAC9PRcGu21DHm6VRhc
Юзернейм и токен уже лежат в проперти файле.
Запускать модули, можно в любой последовательности.
Тесты не писал, из-за не сложной бизнес-логики.
Для добавления, изменения и удаления данных в запрос нужно добавлять токен - 85zESMYPZldjX0kTBf7iNqp-au (примитивная защита от несанкционированого доступа к добавлению/изменению информации)

Примеры запросов:
http://localhost:8080/location/add
POST
{
  "key": "85zESMYPZldjX0kTBf7iNqp-au",
  "name": "Город",
  "description": "Описание"
}

http://localhost:8080/location/update
POST
{
  "key": "85zESMYPZldjX0kTBf7iNqp-au",
  "name": "Город",
  "description": "новое описание"
}

http://localhost:8080/location/delete
POST
{
  "key": "85zESMYPZldjX0kTBf7iNqp-au",
  "name": "Город"
}

