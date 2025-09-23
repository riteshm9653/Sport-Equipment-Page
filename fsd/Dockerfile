FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.war app.war
# Copy WAR file from target folder into container
COPY target/group-project-0.0.1-SNAPSHOT.war app.war

EXPOSE 8089
ENTRYPOINT ["java", "-jar", "app.war"]
