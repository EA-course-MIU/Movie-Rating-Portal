
## Movie Rating Portal

In this project, you will develop a clone of IMDB website. To get a better understanding of the project, check out imdb.com.

In this application, users can create, rate and write comments about movies and tv series. Users can create favorite lists and they can share their lists with other users.

There will be 5 main resource servers:
- Movie Service
    - CRUD operations for movies.
- Tv Series Service
    - CRUD operations for tv series.
- Comment Service
    - CRUD operations for comments.
    - Users can write comment for movies and/or tv series.
- User Service
    - CRUD operations for users.
    - Login and Register functionality.
- Rating Service
    - CRUD operations for ratings.

Your project must have
- Discovery Server
- API Gateway
- Configuration Server
- OAuth 2 Server (Keycloak)
- Tracing (Zipkin and Sleuth)
- Vault


####  Functional  Requirements
--- 
* Users can register to the system.
* Owners can create/remove/update movies and tv series.
* User can filter movies or tv series by :
    * released year,
    * rating,
    * genre,
    * director,
    * actor/actress
    * duration.
* Users can create/remove/update comments for movies/tv series
* Users can rate movies/tv series.
    * Rating is between 1 and 5.
* Users can add/remove movies/tv series to/from their favorite lists.
    * Users can have multiple favorite lists.

#### Technical Details
---
* Use inheritance for tv series and movies.
* Use appropriate hibernate fetch strategies.
* Use RabbitMQ and Kafka.
* Each micro-service must contain a Dockerfile to create a docker image.
* Implement Eventual Consistency.
* Each service must have `data.sql` for dummy data.
* Have 1 Kubernetes Deployment Configuration file.
* Create a Postman collection for each endpoint.
* Create API documentation with Swagger.
* Prepare a docker compose file to run your project.
* Use Circuit Breaker pattern for 5 methods.
* Use ELK Stack to store your application logs.
    * Please refer to https://www.elastic.co/what-is/elk-stack


#### Submission
---
* Upload your source code and project documentation to the Sakai.
* Every member should submit the project to the Sakai.
* Deadline: Sunday Nov 27th at 11:55 PM.
* Project will be evaluated based on your code quality. It is possible that I will need to schedule meetings with students about their source-code.

#### Important Notes
---
* You are not allowed to share codes with your classmates. If detected, you will get NC.

* Remember to respect the code honor submission policy. All written code must be original. Presenting something as one’s own work when it came from another source is plagiarism and is forbidden.

* Plagiarism is a very serious thing in all American academic institutions and is guarded against vigilantly by every professor.
 

