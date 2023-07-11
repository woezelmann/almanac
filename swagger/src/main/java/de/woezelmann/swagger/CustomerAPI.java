package de.woezelmann.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;


@OpenAPIDefinition(
        info = @Info(
                title = "Customer Service",
                version = "0.1",
                description = "Sample service to manage customers"
        ),
        servers = {
                @Server(
                        url = "https://test-servers.com:8080",
                        description = "test server"
                )
        }
)
interface CustomerAPI {

    @PostMapping(path = "customers", consumes = "application/json", produces = "application/json")
    Mono<Customer> createCustomer(Customer customer);
}
