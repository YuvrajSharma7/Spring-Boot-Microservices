# To connect the spring boot to the config server follow below steps

1. Add below dependency

`
        <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
`
2. Add below properties

` application name must be same as properties file connected to config server to fetch properties to connect-
spring.application.name=limits-service
spring.config.import=optional:configserver:http://localhost:8888`