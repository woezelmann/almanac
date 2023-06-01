package de.woezelmann;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ReverseController {
    @PostMapping(value = "/aop/reverse", consumes = "text/plain", produces = "text/plain")
    public Mono<String> reverse(@RequestBody String value) {
        return Mono.just(new StringBuilder(value))
                .map(StringBuilder::reverse)
                .map(StringBuilder::toString);
    }

    @PostMapping(value = "/aop/reverse-with-aspect", consumes = "text/plain", produces = "text/plain")
    public Mono<String> reverseWithAspect(@RequestBody String value) {
        return Mono.just(new StringBuilder(value))
                .map(StringBuilder::reverse)
                .map(StringBuilder::toString);
    }
}
