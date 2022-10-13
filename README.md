# Packet Tracker Service
This application helps users collect statistics for all incoming packets

## Initial set up
1) Clone the repository using HTTPS or SSH key

# Database Set up
1) Install [MySQL Community Server 8.0.31](https://dev.mysql.com/downloads/mysql/)
2) Install [MySQL Workbench](https://dev.mysql.com/downloads/workbench/) by setting a username and password 
3) Start Workbench and open a file named [create_database_and_table_query.sql](https://github.com/varadjos89/Cisco_Meraki/blob/master/create_database_and_table_query.sql) from the cloned repository and run commands in it to create your database and table.

# Back-end Set up
The Backend is built using Java and uses Spring Boot as a web framework. Following are the steps reuired to setup the backend service
1) Install Java 11 locally 
2) Open a project named packet-tracker-rest-service using IntelliJ/ Eclipse / NetBeans as a Maven project.
3) Install all the dependencies from [pom.xml](https://github.com/varadjos89/Cisco_Meraki/blob/master/packet-tracker-rest-service/pom.xml) using mvn install.
4) Update a file named [application.properties](https://github.com/varadjos89/Cisco_Meraki/blob/main/packet-tracker-rest-service/src/main/resources/application.properties) with username and password from database setup steps

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/recordsDB?createDatabaseIfNotExist=true
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.generate-ddl=true
```

5) Go to test package to run postive and negative test.

# Decisions that I have made and what I would do if I get more time
1) For the backend service, I decided to go with Spring Boot as a web framework. If, I get more time, I would divided this service into two different microservices, where one would accept all the incoming packets and perform calculations for min, max and avg where as the other service would handle get all packets opertions for the users, who want to see statictics at any point of time.
2) I would dockerize both the service and deploy them on the kubernetes cluster. The cluster would perform load balancing, deploy new contianers if incoming load goes up, destroy extra containers if the load goes up and perform health checks time to time. Additional advantage include deploying new version without compromising current users.
3) I would also add a event based queue in front of our backend microservices to handle calls asynchronously and also to make sure we are not blocking any incoming calls if incoming load suddenly goes up. 
4) I would also creata a dead letter queue which would include all the failed requests so we can use it to retry all the failed calls.
5) For security, I would go with mTLS based authentication to make all the calls are secure. mTLS performs authentication not only at the clint side but also at the service side, so we get both client and server authenticated to perform any operations.
6) For security, I would convert current HTTP calls into HTTPS so that all the calls would autmocatically get encrypted.
7) For database, I used MySQL to support ACID based properties. We can improves its performace by creating index for device_id and timestamp_start columns. I would also create a date pipeline which will transfer data from the current table into a secondary table so that the primary one would have less records while performing any operation which would also improve its efficiency.
