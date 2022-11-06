# https://github.com/linianhui/docker/pkgs/container/openjdk
FROM ghcr.io/linianhui/openjdk:8u322

ARG JAR_FILE

WORKDIR /app

COPY ${JAR_FILE} app.jar

ENV JAVA_OPTIONS='-XX:+UseG1GC -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.00 -XX:+FlightRecorder -XX:StartFlightRecording=duration=10s,disk=true,dumponexit=true,filename=1.jfr'

ENTRYPOINT ["/bin/bash", "-c", "java $JAVA_OPTIONS $JAVA_DEBUG_OPTIONS -jar app.jar"]

EXPOSE 80 5005
