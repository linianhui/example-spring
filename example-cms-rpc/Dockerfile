# https://hub.docker.com/r/lnhcode/openjdk/tags
FROM ghcr.io/linianhui/openjdk:11.0.10

ARG JAR_FILE

WORKDIR /app

COPY JAR_FILE app.jar

ENV JAVA_OPTIONS='-XX:+UseG1GC -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.00'

ENTRYPOINT ["/bin/bash", "-c", "java $JAVA_OPTIONS $JAVA_DEBUG_OPTIONS -jar app.jar"]

EXPOSE 80 5005
