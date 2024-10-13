FROM openjdk:24-slim

WORKDIR /app

ARG profile=dev
ENV target_profile=${profile}

COPY /target/EasyToDo-0.0.1-SNAPSHOT.jar EasyToDo.jar

ENTRYPOINT ["java", "-jar", "EasyToDo.jar"]
CMD ["--spring.profiles.active=${target_profile}"]
