package de.woezelmann;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;
import reactor.core.publisher.Mono;

@Slf4j
@ControllerAdvice
public class StorageExceptionHandler {

    @ExceptionHandler
    public Mono<ResponseEntity<ThrowableProblem>> handleException(StorageException ex) {
        if (ex.getHttpCode() >= 400 && ex.getHttpCode() < 500) {
            log.warn(ex.getMessage());
        } else if (ex.getHttpCode() >= 500) {
            log.error(ex.getMessage());
        }

        return Mono.just(ResponseEntity
                .status(ex.getHttpCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Problem.builder()
                        .withStatus(Status.valueOf(ex.getHttpCode()))
                        .withDetail(ex.getMessage())
                        .build()));
    }
}
