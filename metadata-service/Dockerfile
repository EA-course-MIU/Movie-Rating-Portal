FROM openjdk:17
LABEL MAINTAINER="Kelvin Le"
LABEL APPLICATION="Metadata Service"
WORKDIR ./
COPY target/metadata-service-0.0.1-SNAPSHOT.jar metadata-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","metadata-service-0.0.1-SNAPSHOT.jar"]
#EXPOSE 7071