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

WORKDIR /app

#COPY --from=builder /src/target/api-0.0.1-SNAPSHOT.jar app.jar
COPY target/api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8091

ENTRYPOINT ["java", "-jar","app.jar"]
