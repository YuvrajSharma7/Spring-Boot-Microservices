# To create a eureka server follow below steps

1. Add below dependency in pom.xml

`<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>`

2. Add @EnableEurekaServer annotation on @SpringBootApplication annotation
3. Add below properties in application.properties file

`to avoid this eureka server to register itself as a eureka client
   eureka.client.register-with-eureka=false
   eureka.client.fetch-registry=false`