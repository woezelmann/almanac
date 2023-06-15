package de.woezelmann.graalvm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PoliteController {

    @GetMapping("/say-hello/{name}")
    public Mono<String> hello(@PathVariable("name") String name) {
        return Mono.just("hello, " + name);
    }
}
