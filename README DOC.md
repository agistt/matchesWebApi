[Project Technologies/Dependecies:]
- Java 17
- SpringBoot
- JPA
- PostgreSQl 14.2
- Maven
- embedded TomcatServer
- Lombok (used lombok in order to have better readability and less boilerplate code)

In order to test the implemented functionalities used Postman to generate the requests

Main pusprose for this web api project is to handle basic CRUD request for two entities **Match**, **MatchOdds**
For each οf these two entities was made 2 respectively **Cotrollers** and **Repository**. Each Repository extends **JpaRepository**
in order to have basic functionalities from jpa (create,read,update,delete etc).

Each **Controller** implements basic HTTP requests using spring web annotation Mapping for those incoming requests.
Used **@RequestBody, @PathVariable, @RequestParam** to get incoming Object,values.

Created entity Match with id,description,team_a,team_b,match_time,match_date,sport.
Used hibernate in order to autocreate tables in our database based on our Entities values and properites. Also
create MatchOdds entity with **@ManytoOne** connection with Match entity in order to have a ForeignKey on **match_id** between
these two Entities.

In application.properties is defined all properties that are needed in order JPA connect
to database (creds and other hibernates properties to autogenerate database).

> For testing this WebAPI have to make some basic modifications in credentials(username,password,url) in application.properties
> in order to connect successfully to the database. First time running set spring.jpa.hibernate.ddl-auto= update value to create 
> in order to create the tables in DB

#### Some executed Requests examples from Postman:
[image](img.png "createMatchRequest")
[image](img_1.png "getAllMatches")
[image](img_2.png "createMatchOdd")