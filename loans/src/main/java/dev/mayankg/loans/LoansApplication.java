package dev.mayankg.loans;

import dev.mayankg.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Mayank Gupta
 * @Link: <a href=http://localhost:8090/swagger-ui/index.html>Swagger for Loans Microservice</a>
 */
@OpenAPIDefinition(
        info = @Info(
                title = "FinVista Nexus's Loans microservice REST API Documentation",
                description = "FinVista Nexus - Loans microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Mayank Gupta",
                        email = "techno.sorcerer3@gmail.com",
                        url = "https://github.com/MayankGupta-dev08"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.thetechnosorcerers.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "FinVista Nexus - Loans microservice REST API Documentation",
                url = "https://www.finvista-nexus.com/swagger-ui.html"
        ),
        servers = @Server(
                description = "Loans server"
        )
)
@SpringBootApplication
@EnableConfigurationProperties(value = {LoansContactInfoDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class LoansApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

}