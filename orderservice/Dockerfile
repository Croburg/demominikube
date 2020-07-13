FROM openjdk:10.0.2-jre-slim
COPY target/orderservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]