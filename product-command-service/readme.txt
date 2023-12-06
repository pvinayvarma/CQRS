README for the Command Query Responsibility Segregation (CQRS) pattern implementation project. CQRS is a software architectural pattern that separates the read and write operations of a system into distinct components, addressing the challenges of scalability and maintainability. This document provides an overview of the CQRS pattern implementation project, along with instructions on how to use and contribute to the codebase.

Prerequisites
1)Java: Kafka requires Java to be installed. Make sure you have Java installed on your machine. You can download it from Java's official website or use OpenJDK.

2)Download Kafka: Visit the Apache Kafka Downloads page and download the latest stable release. Extract the downloaded archive to a location of your choice.

for example keep the extraction folder in c:/kafka

open the cmd from c:/kafka and run the commands to run zoomkeeper and kafka

command to run zoomkeeper -- default for 2181
C:\kafka>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

comand to run kafka server -- default for 9092
C:\kafka>.\bin\windows\kafka-server-start.bat .\config\server.properties

Steps for implementation CQRS :
1) Create 2 micro service Java project using a build tool like Maven or Gradle. Add the necessary dependencies for your application, such as the web framework (e.g., Spring Boot), database drivers,spring-kafka, and any other libraries you may need.
2)Separate your application's commands (write operations) into one micro service and queries (read operations) into another micro service.
3)Create,update product the rest endpoints in write operations Micro service
4)Fetch product the rest endpoints in Read operations Micro service
5)Configure your application to use appropriate data sources for both the write and read sides.
6)Configure Kafka properties in the application.yml file in write operations Micro service as a producer
	ex:
	spring:
	  kafka:
		producer:
		  bootstrap-servers: localhost:9092
		  key-serializer: org.apache.kafka.common.serialization.StringSerializer
		  value-serializer:  org.springframework.kafka.support.serializer.JsonSerializer
7)Configure Kafka properties in the application.yml file in Read operations Micro service as a consumer
	ex:
	spring:
	  kafka:
		consumer:
		  bootstrap-servers: localhost:9092
		  key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
		  value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
		  properties:
			spring:
			  json:
				trusted:
				  packages: com.cqrs.demo.dto

8)Create event in Write operations to send messages to kafka cluster
	samle code : kafkaTemplate.send("product-event-kafka-topic",event);
9)same event has to consume in read operations by annotating method as @KafkaListener(topics = "product-event-kafka-topic",groupId = "product-event-group")
10) Based on the event do the operations
