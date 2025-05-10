FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/employeeinfo.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
