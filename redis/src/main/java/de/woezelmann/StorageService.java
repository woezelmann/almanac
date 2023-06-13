package de.woezelmann;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class StorageService {

    @Autowired
    private ReactiveRedisTemplate<String, Customer> redisTemplate;

    public Mono<Customer> storeData(Customer customer) {
        String id = UUID.randomUUID().toString();
        Customer newCustomer = new Customer(id, customer.firstName(), customer.lastName());

        // TODO: use more fancy code
        return redisTemplate.opsForValue()
                .set(id, newCustomer)
                .flatMap(success -> {
                    if (success) {
                        return Mono.just(newCustomer);
                    } else {
                        return Mono.error(new StorageException("could not store data to redis", 500));
                    }
                });
    }

    public Mono<Customer> fetchData(String id) {

        return redisTemplate.opsForValue()
                .get(id)
                .switchIfEmpty(Mono.error(new StorageException("could not fetch resource with id " + id, 404)));

    }
}
