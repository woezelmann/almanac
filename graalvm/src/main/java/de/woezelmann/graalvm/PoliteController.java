package de.woezelmann.graalvm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class PoliteController {

    @GetMapping("/say-hello/{name}")
    public Mono<String> hello(@PathVariable("name") String name) {
        log.info("saying hello to {}", name);
        return Mono.just("hello, " + name);
    }
}
