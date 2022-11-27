FROM openjdk:18
LABEL MAINTAINER="Ba Luan Tran"
LABEL APPLICATION="Movie Service"
WORKDIR ./
COPY target/movie-service-0.0.1-SNAPSHOT.jar movie-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","movie-0.0.1-SNAPSHOT.jar"]
#EXPOSE 7071