package de.woezelmann;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/customer")
    public Mono<Customer> createResource(@RequestBody Customer customer) {
        return storageService.storeData(customer);
    }

    @GetMapping("/customer/{id}")
    public Mono<Customer> fetchResource(@PathVariable("id") String id) {
        return storageService.fetchData(id);
    }
}
