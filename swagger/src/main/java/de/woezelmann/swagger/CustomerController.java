package de.woezelmann.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController implements CustomerAPI {

    @Autowired
    private CustomerStorage storage;


    public Mono<Customer> createCustomer(Customer customer) {
        return storage.storeCustomer(customer);
    }
}
