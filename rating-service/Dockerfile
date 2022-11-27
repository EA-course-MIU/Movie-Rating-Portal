FROM openjdk:18
LABEL MAINTAINER="Ba Luan Tran"
LABEL APPLICATION="Rating Service"
WORKDIR ./
COPY target/rating-service-0.0.1-SNAPSHOT.jar rating-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","rating-0.0.1-SNAPSHOT.jar"]