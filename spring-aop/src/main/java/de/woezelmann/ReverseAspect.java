package de.woezelmann;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Aspect
@Component
public class ReverseAspect {

    @Around("execution(reactor.core.publisher.Mono<String> de.woezelmann.ReverseController.reverseWithAspect(..))")
    public Object lockOnSingle(ProceedingJoinPoint pjp) throws Throwable {

        Mono<String> proceed = (Mono<String>) pjp.proceed();

        return proceed.map(String::toUpperCase);
    }
}
