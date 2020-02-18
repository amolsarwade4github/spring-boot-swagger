# Spring Boot + Swagger
Add Swagger into Spring Boot Application and configure

# Getting Started

* Getting the Swagger 2 Spring Dependency
* Enabling Swagger in your code
* Configuring Swagger
* Adding details as annotations to APIs

#Step By Step

#### Getting the Swagger 2 Spring Dependency
* Add below listed dependencies into pom.xml
        
        <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
        
 * Enabling Swagger in your code
 
         import org.springframework.context.annotation.Configuration;
         import springfox.documentation.swagger2.annotations.EnableSwagger2;
         
         @Configuration
         @EnableSwagger2
         public class SwaggerConfig {
         
         }
 
 * Configuring Swagger
         package com.example.swagger.config;
         
         import org.springframework.context.annotation.Bean;
         import org.springframework.context.annotation.Configuration;
         import springfox.documentation.builders.PathSelectors;
         import springfox.documentation.builders.RequestHandlerSelectors;
         import springfox.documentation.service.ApiInfo;
         import springfox.documentation.service.Contact;
         import springfox.documentation.spi.DocumentationType;
         import springfox.documentation.spring.web.plugins.Docket;
         import springfox.documentation.swagger2.annotations.EnableSwagger2;
         
         import java.util.Collections;
         
         @Configuration
         @EnableSwagger2
         public class SwaggerConfig {
         
             @Bean
             public Docket swaggerConfiguration() {
                 return new Docket(DocumentationType.SWAGGER_2)
                         .select()
                             .paths(PathSelectors.ant("/api/**"))
                             .apis(RequestHandlerSelectors.basePackage("com.example.swagger"))
                             .build()
                         .apiInfo(apiDetails());
             }
         
             private ApiInfo apiDetails() {
                 return new ApiInfo(
                         "Address Book API",
                         "Sample APIs for POC",
                         "1.0",
                         "Open source",
                         new Contact(
                                 "Amol Sarwade",
                                 "https://github.com/amolsarwade4github",
                                 "amol.sarwade91@gmail.com"),
                         "API License",
                         "https://github.com/amolsarwade4github/spring-boot-swagger",
                         Collections.emptyList()
                 );
             }
         
         }

 * Adding details as annotations to APIs
        
      **Resource Level:**
      
          package com.example.swagger.resources;
          
          import com.example.swagger.model.Contact;
          import io.swagger.annotations.ApiOperation;
          import io.swagger.annotations.ApiParam;
          import org.springframework.web.bind.annotation.*;
          
          import java.util.ArrayList;
          import java.util.List;
          import java.util.concurrent.ConcurrentHashMap;
          
          @RestController
          @RequestMapping("/api/contacts")
          public class AddressBookResource {
          
              ConcurrentHashMap<String, Contact> contacts = new ConcurrentHashMap<>();
          
              @GetMapping
              public List<Contact> getAllContacts() {
                  return new ArrayList<>(contacts.values());
              }
          
              @GetMapping(path = "{id}")
              @ApiOperation(
                      value = "Finds Contact by id",
                      notes = "Provide an id to look up specific Contact from the address book",
                      response = Contact.class
              )
              public Contact getContact(
                      @ApiParam(value = "ID value for the Contact you need to retrive", required = true)
                      @PathVariable("id") String id) {
                  return contacts.get(id);
              }
          
              @PostMapping
              public Contact createContact(@RequestBody Contact contact) {
                  return contacts.put(contact.getId(), contact);
              }
          
          }
      
      **Model Level:**
      
          package com.example.swagger.model;
          
          import io.swagger.annotations.ApiModel;
          import io.swagger.annotations.ApiModelProperty;
          
          @ApiModel(description = "Details about the Contact")
          public class Contact {
          
              @ApiModelProperty(notes = "The unique id of the Contact")
              private String id;
          
              @ApiModelProperty(notes = "The person's name")
              private String name;
          
              @ApiModelProperty(notes = "The person's phone number")
              private String phone;
              
          }
  * **Note:** Mixing Swagger configuration with code is not a good idea, there is a solution for it and that is **Spring Rest Docs**.     