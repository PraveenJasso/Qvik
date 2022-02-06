# Qvik

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/#build-image)

### Requirements
* Java 1.8
* Maven
* PostgresSQL

### Steps

1. Clone the project from Git
2. Import as maven project in your IDE
3. Create Database based on the application.properties
4. Run create_schema.sql file to create app related and Batch related default tables
5. Run the Spring application

Note : Once we started the sprint boot application automatically batch will trigger to clean the data

### APIs

1. POST API:
	http://localhost:8080/
	Body Content : {"name":"Test","logId":1,"message":"test"}
2. Read API:
	http://localhost:8080/