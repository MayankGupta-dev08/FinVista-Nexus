# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Label for the author
LABEL authors="Mayank Gupta"

# Information about who maintains the image
MAINTAINER "https://github.com/MayankGupta-dev08"

# Add the application's jar to the image
COPY target/accounts-1.0.1-SNAPSHOT.jar accounts-1.0.1-SNAPSHOT.jar

# Expose the debug port, such that container listens to it on runtime
EXPOSE 5005

# Execute the application with debug options
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "accounts-1.0.1-SNAPSHOT.jar"]