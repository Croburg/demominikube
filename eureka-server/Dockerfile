FROM openjdk:10.0.2-jre-slim
COPY target/eureka-server-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app.jar"]