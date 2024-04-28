FROM amazoncorretto:21
COPY target/mock-service-1.0.0.jar mock-service-1.0.0.jar
ENTRYPOINT ["java", "-Xms512M", "-Xmx1024M", "-jar", "/mock-service-1.0.0.jar"]