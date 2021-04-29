FROM maven:3.6.1-jdk-11-slim AS build
WORKDIR /home/app
COPY pom.xml .

RUN mvn dependency:go-offline

COPY src/ ./src

RUN mvn clean package

FROM openjdk:11-jre-slim

RUN addgroup back && adduser --no-create-home --disabled-password --ingroup back back
USER back

COPY --from=build /home/app/target/backend-*.jar app.jar

CMD [ "java", "-jar", "/app.jar" ]
