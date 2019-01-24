FROM postgres:9.4  

FROM maven:3.6.0-jdk-8-alpine

COPY pom.xml /tmp/
COPY src /tmp/src/

WORKDIR /tmp/

RUN ["mvn", "package"]
