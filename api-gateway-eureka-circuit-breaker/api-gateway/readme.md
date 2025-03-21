## To setup API gateway follow below steps

1. Add below dependencies-

`<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-gateway</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>`
2. Add below property

` #eureka server Url
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka`
3. 