FROM openjdk:18
LABEL MAINTAINER="Viet Tri Le"
LABEL APPLICATION="Comment Service"
WORKDIR ./
COPY target/comment-service-0.0.1-SNAPSHOT.jar comment-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","comment-0.0.1-SNAPSHOT.jar"]

#EXPOSE 80

