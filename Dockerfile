FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY mvnw mvnw.cmd ./
COPY .mvn/ .mvn/

COPY pom.xml .
COPY src/ ./src/

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests




FROM gcr.io/distroless/java17

COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]