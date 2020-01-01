# https://hub.docker.com/_/maven
#FROM maven:3.6-jdk-8 as builder
#
#USER root
#
#COPY . /src/
#
#WORKDIR /src
#
#RUN mvn package

# https://hub.docker.com/_/openjdk
FROM openjdk:8-jre-alpine

ARG JAR_FILE

WORKDIR /app

#COPY --from=builder /src/target/spring-example-service1-0.0.1-SNAPSHOT.jar service1.jar
COPY target/${JAR_FILE} app.jar

EXPOSE 80

ENTRYPOINT ["java", "-jar", "app.jar"]
