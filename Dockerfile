FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt-get -y update; apt-get -y install curl
RUN apt-get update && apt-get install -y unzip
COPY . .

RUN curl -L https://services.gradle.org/distributions/gradle-8.2.1-bin.zip -o gradle-8.2.1-bin.zip
RUN unzip gradle-8.2.1-bin.zip
ENV GRADLE_HOME=$APP_HOME/gradle-8.2.1
ENV PATH=$PATH:$GRADLE_HOME/bin
RUN gradle --version
RUN gradle wrapper
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /build/libs/ams-0.0.1-SNAPSHOT.jar ams.jar

ENTRYPOINT ["java", "-jar", "ams.jar"]