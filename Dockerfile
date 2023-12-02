FROM openjdk:17
VOLUME /tmp
ADD ./target/medijoven-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]