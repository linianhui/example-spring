# https://hub.docker.com/_/openjdk
FROM openjdk:11.0.5

ARG JAR_FILE

WORKDIR /app

COPY target/${JAR_FILE} app.jar

EXPOSE 80

ENTRYPOINT ["java", "-jar", "app.jar"]
