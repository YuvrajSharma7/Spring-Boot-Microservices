# To make a spring boot app as config server
1.  Add below dependency
` <dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-config-server</artifactId>
   </dependency>
`
2.  Add below annotation on @SpringBootApplication
`@EnableConfigServer`
3. Add below properties in application.properties file
`spring.application.name=spring.cloud.config.server
 Default port for config server-
server.port=8888
 path fo the local properties file from which the config server will fetch the properties-
spring.cloud.config.server.git.uri=file:///C:/Documents/SpringBootMicroservices/git-local-config-repo`
4. To access the properties of default profile corresponding to limits-service.properties file use 
below url where after base url 'limits-service' is as per the default properties file name in the 
local git repo and whose path is mentioned in this app (config server) properties and
'default' is the active profile name of the application

`http://localhost:8888/limits-service/default`

5. To access environment specific properties replace the default in the above url with dev/qa or so
