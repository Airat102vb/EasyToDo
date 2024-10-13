#!/bin/bash

#Скрипт удаляет контейнер и образ, собирает новый образ и запускает контейнер из нового образа
#Нестандартные настройки: network mycustomnetwork; port 8088:8088

CONTAINER_NAME=so-easy
IMAGE_NAME=easy
APP_PROFILE="${1:-dev}"

mvn clean package && echo -e " --------------------------
| Сборка завершена успешно |
 --------------------------"

# Удаление существующего контейнера
if [ "$(sudo docker ps -q -f name=$CONTAINER_NAME)" ]; then
  echo -e "Контейнер '$CONTAINER_NAME' запущен.
Остановка контейнера '$CONTAINER_NAME'"
  sudo docker stop $CONTAINER_NAME && sudo docker wait $CONTAINER_NAME
  echo "Контейнер '$CONTAINER_NAME' успешно остановлен"
  echo "Удаление контейнера '$CONTAINER_NAME'" && sudo docker rm $CONTAINER_NAME
else
  echo "Контейнер '$CONTAINER_NAME' не запущен."
  if [ "$(sudo docker ps -a -q -f name=$CONTAINER_NAME)" ]; then
    echo "Существует остановленный контейнер '$CONTAINER_NAME'"
    echo "Удаление остановленного контейнера '$CONTAINER_NAME'" && sudo docker rm $CONTAINER_NAME
  else
    echo "Контейнера '$CONTAINER_NAME' не существует"
  fi
fi

#Удаление образа
echo "Удаление образа '$IMAGE_NAME'" && sudo docker rmi -f $IMAGE_NAME

#Создание нового образа
echo "Создание нового образа '$IMAGE_NAME'"
sudo docker build --build-arg profile=$APP_PROFILE -t $IMAGE_NAME .

#Запуск контейнера
echo "Запуск контейнера '$CONTAINER_NAME' из образа '$IMAGE_NAME' с профилем $APP_PROFILE"
sudo docker run -d --name $CONTAINER_NAME --network mycustomnetwork -p 8088:8088 $IMAGE_NAME
