FROM openjdk:18
LABEL MAINTAINER=""
LABEL APPLICATION="User service"
WORKDIR ./
COPY target/user-service-0.0.1-SNAPSHOT.jar user-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","user-service-0.0.1-SNAPSHOT.jar"]