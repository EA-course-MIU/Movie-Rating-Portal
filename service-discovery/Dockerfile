FROM openjdk:18
LABEL MAINTAINER="Viet Tri Le"
LABEL APPLICATION="Service Discovery"
WORKDIR ./
COPY target/service-discovery-0.0.1-SNAPSHOT.jar discovery-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","discovery-0.0.1-SNAPSHOT.jar"]

EXPOSE 80

