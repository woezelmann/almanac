package de.woezelmann.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Operation(description = "this resource allows for the creation of new customers", summary = "create a new customer")
    Mono<Customer> createCustomer(@Parameter(required = true, description = "customer that should be created. id will be generated") Customer customer);

    @GetMapping(path = "customers/{id}", produces = "application/json")
    @Operation(description = "this resource allows to fetch a customer by id", summary = "fetch customer by id")
    Mono<Customer> fetchCustomer(@PathVariable String id);
}
