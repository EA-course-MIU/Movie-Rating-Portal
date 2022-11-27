FROM openjdk:18
LABEL MAINTAINER="Viet Tri Le"
LABEL APPLICATION="api gateway"
WORKDIR ./
COPY target/api-gateway-0.0.1-SNAPSHOT.jar gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","gateway-0.0.1-SNAPSHOT.jar"]

EXPOSE 80

