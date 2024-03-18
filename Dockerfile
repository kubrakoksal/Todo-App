FROM openjdk:17-jdk-alpine
# FROM ubuntu (every docker image must be based off of another image)
MAINTAINER kkoksal
# RUN apt-get update
COPY target/*.jar /todo-app.jar
ENTRYPOINT ["java", "-jar", "/todo-app.jar"]