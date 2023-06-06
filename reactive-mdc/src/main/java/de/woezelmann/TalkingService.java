package de.woezelmann;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static de.woezelmann.MdcHelper.logOnNext;

@Service
public class TalkingService {
    private final Logger logger = LoggerFactory.getLogger(TalkingService.class);

    public Mono<String> sayHello() {
        return Mono.just("Hello, world!!!")
                .doOnEach(logOnNext(s -> logger.info("Controller is saying: {}", s)));
    }

    public Mono<String> sayNothing() {
        return Mono.error(new MuteException());
    }
}
