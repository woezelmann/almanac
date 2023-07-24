package de.woezelmann.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.server.EntityResponse;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Throwable.class)
    @ApiResponse(
            responseCode = "500",
            description = "an general error occurred while processing",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class)))
    public Mono<EntityResponse<ThrowableProblem>> generalError(Throwable throwable) {

        return EntityResponse
                .fromObject(Problem.valueOf(Status.INTERNAL_SERVER_ERROR, throwable.getMessage()))
                .build();
    }

    @ExceptionHandler(CustomerException.class)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "404",
                    description = "customer not found",
                    content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))),
            @ApiResponse(
                    responseCode = "409",
                    description = "customer already exists",
                    content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))),
    })
    public Mono<EntityResponse<ThrowableProblem>> customerAlreadyExists(CustomerException exception) {
        return EntityResponse
                .fromObject(Problem.valueOf(Status.valueOf(exception.httpStatus), exception.getMessage()))
                .build();
    }
}
