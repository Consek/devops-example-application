FROM maven:3.6.3-jdk-11-slim AS build
WORKDIR /home/app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /home/app/src
RUN mvn clean package -Dmaven.test.skip

FROM openjdk:11-jdk-slim
COPY --from=build /home/app/target/backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
