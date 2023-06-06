package de.woezelmann;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveMdcController {


    @Autowired
    private TalkingService talkingService;

    @GetMapping(value = "/reactive-mdc/hello", produces = "text/plain")
    public Mono<String> sayHello() {

        return talkingService.sayHello();
    }

    @GetMapping(value = "/reactive-mdc/mute", produces = "text/plain")
    public Mono<String> sayNothing() {

        return talkingService.sayNothing();
    }
}
