## To make the application a Eureka client follow below steps

1. Add below dependency to the pom.xml

`<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>`
2. Add below property for Eureka server Url

`# eureka server Url
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka`