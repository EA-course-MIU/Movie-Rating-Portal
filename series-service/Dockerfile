FROM openjdk:17
LABEL MAINTAINER="Trong Hoang"
LABEL APPLICATION="Series Service"
WORKDIR ./
COPY target/series-service-0.0.1-SNAPSHOT.jar series-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","series-service-0.0.1-SNAPSHOT.jar"]
