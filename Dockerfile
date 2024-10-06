FROM openjdk:24-slim

WORKDIR /app

COPY /target/EasyToDo-0.0.1-SNAPSHOT.jar EasyToDo.jar

ENTRYPOINT ["java", "-jar", "EasyToDo.jar"]
