#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#Label for the authour
LABEL authors="Mayank Gupta"

#Information around who maintains the image
MAINTAINER "https://github.com/MayankGupta-dev08"

# Add the application's jar to the image
COPY target/accounts-1.0.1-SNAPSHOT.jar accounts-1.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "accounts-1.0.1-SNAPSHOT.jar"]