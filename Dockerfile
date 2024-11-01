FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . /app/
RUN mvn clean package

#
# Package stage
#
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080

#ENV SPRING_PROFILES_ACTIVE=prod,common

ENTRYPOINT ["java","-jar","app.jar"]