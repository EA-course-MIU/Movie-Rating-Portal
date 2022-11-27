FROM openjdk:17
LABEL MAINTAINER="Trong Hoang"
LABEL APPLICATION="Series Service"
WORKDIR ./
COPY target/service-discovery-0.0.1-SNAPSHOT.jar service-discovery-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","service-discovery-0.0.1-SNAPSHOT.jar"]
