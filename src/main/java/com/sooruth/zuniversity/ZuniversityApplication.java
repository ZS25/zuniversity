package com.sooruth.zuniversity;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition( //http://localhost:8090/swagger-ui/index.html
        info = @Info(
                title = "Zuniversity application",
                version = "0.0.6-SNAPSHOT",
                description = "This project is made to try out the new features of Spring Boot 3.* over Java 21",
                termsOfService = "sooruth",
                contact = @Contact(
                        name = "Zulfekar",
                        email = "https://www.linkedin.com/in/zulfekarsooruth"
                ),
                license = @License(
                        name = "License",
                        url = "https://github.com/ZS25"
                )
        )
)
public class ZuniversityApplication {
    private static final Logger LOG = LoggerFactory.getLogger(ZuniversityApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ZuniversityApplication.class, args);
        LOG.info("Welcome to Zuniversity!");
        LOG.debug("Classpath: " + System.getProperty("java.class.path"));
    }

}
