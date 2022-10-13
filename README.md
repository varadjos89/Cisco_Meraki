# Packet Tracker Service
This application helps users collect statistics for all incoming packets

## Initial set up
1) Clone the repository using HTTPS or SSH key

# Database Set up
1) Install [MySQL Community Server 8.0.31](https://dev.mysql.com/downloads/mysql/)
2) Install [MySQL Workbench](https://dev.mysql.com/downloads/workbench/) by setting a username and password 
3) Start Workbench and open a file named [create_database_and_table_query.sql(https://github.com/varadjos89/Cisco_Meraki/blob/master/create_database_and_table_query.sql) from the cloned repository and run commands in it to create your database and table.

# Back-end Set up
1) The Backend is built using Java and uses Spring Boot as a web framework.
2) Install Java 11 locally 
2) Open a project named packet-tracker-rest-service using IntelliJ/ Eclipse / NetBeans as a Maven project.
3) Install all the dependencies from [pom.xml](https://github.com/varadjos89/Cisco_Meraki/blob/master/packet-tracker-rest-service/pom.xml) using mvn install.
4) Update the below file named application.properties with username and password from database setup steps

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/recordsDB?createDatabaseIfNotExist=true
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.generate-ddl=true
```

5) Go to test package to run postive and negative test.
