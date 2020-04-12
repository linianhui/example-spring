# https://hub.docker.com/r/lnhcode/openjdk/tags
FROM lnhcode/openjdk:8u242

RUN ln -sf /bin/bash /bin/sh

ARG JAR_FILE

WORKDIR /app

COPY target/${JAR_FILE} app.jar

ENV JAVA_OPTIONS='-XX:+UseG1GC -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.00'

EXPOSE 80

RUN ln -sf /bin/bash /bin/sh

ENTRYPOINT java $JAVA_OPTIONS -jar app.jar
