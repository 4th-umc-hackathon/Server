FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} gpt-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/gpt-0.0.1-SNAPSHOT.jar"]