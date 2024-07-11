FROM openjdk:17.0.11

WORKDIR /app

COPY ./target/monitoreo-0.0.1.SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar","monitoreo-0.0.1-SNAPSHOT.jar"]