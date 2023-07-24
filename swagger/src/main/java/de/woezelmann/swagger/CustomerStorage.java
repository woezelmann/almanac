package de.woezelmann.swagger;


import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CustomerStorage {

    private Map<String, Customer> customersById = new HashMap<>();

    public Mono<Customer> storeCustomer(Customer customer) {
        Customer newCustomer = customer.withId(UUID.randomUUID().toString());
        customersById.put(newCustomer.id(), customer);
        return Mono.just(customer);
    }

    public Mono<Customer> fetchCustomer(String id) {
        if (!customersById.containsKey(id)) {
            return Mono.error(new CustomerException("Could not find customer", 404));
        }

        return Mono.just(customersById.get(id));
    }
}
