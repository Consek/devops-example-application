FROM maven:3.6.3-jdk-11-slim AS build
WORKDIR /home/app
COPY ./ /home/app
RUN --mount=type=cache,target=/root/.m2  mvn clean package -Dmaven.test.skip

FROM openjdk:11-jdk-slim
COPY --from=build /home/app/target/backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
