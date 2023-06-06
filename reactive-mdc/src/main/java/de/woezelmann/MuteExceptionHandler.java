package de.woezelmann;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static de.woezelmann.MdcHelper.logOnNext;

@ControllerAdvice
public class MuteExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(MuteExceptionHandler.class);

    @ExceptionHandler
    public Mono<ResponseEntity<String>> handleException(MuteException ex, ServerWebExchange request) {
        return Mono.just(ResponseEntity
                        .status(500)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body("Computer says no"))
                .doOnEach(logOnNext(s -> logger.error("Computer did not want to speak")));
    }
}
