FROM openjdk:17
LABEL MAINTAINER="Trong Hoang"
LABEL APPLICATION="Favorite Service"
WORKDIR ./
COPY target/favorite-service-0.0.1-SNAPSHOT.jar favorite-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","favorite-service-0.0.1-SNAPSHOT.jar"]
