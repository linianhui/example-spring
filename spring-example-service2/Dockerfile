# https://hub.docker.com/r/lnhcode/openjdk/tags
FROM lnhcode/openjdk:11.0.6

ARG JAR_FILE

WORKDIR /app

COPY target/${JAR_FILE} app.jar

EXPOSE 80

ENTRYPOINT ["java", "-jar", "app.jar"]
