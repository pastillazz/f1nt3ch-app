
FROM eclipse-temurin:21.0.11_10-jdk-ubi10-minimal AS builder

WORKDIR /app

COPY ./pom.xml /app
COPY ./.mvn /app/.mvn
COPY ./mvnw /app


RUN ./mvnw dependency:go-offline

COPY ./src /app/src
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:21-jre-ubi10-minimal
WORKDIR /app

COPY --from=builder /app/target/f1nt3ch-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]