FROM openjdk:17
LABEL MAINTAINER="Trong Hoang"
LABEL APPLICATION="Config Server"
WORKDIR ./
COPY target/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","config-server-0.0.1-SNAPSHOT.jar"]
#EXPOSE 7071