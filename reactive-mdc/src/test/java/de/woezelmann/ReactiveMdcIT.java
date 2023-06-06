package de.woezelmann;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class ReactiveMdcIT {

    @Autowired
    private WebTestClient client;

    @Test
    void testLoggingWithMdc() {
        client.get()
                .uri("/reactive-mdc/hello")
                .accept(MediaType.TEXT_PLAIN)
                .header("x-flow-id", UUID.randomUUID().toString())
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).isEqualTo("Hello, world!!!");
    }

    @Test
    void testExceptionMapperLoggingWithMdc() {
        client.get()
                .uri("/reactive-mdc/mute")
                .accept(MediaType.TEXT_PLAIN)
                .header("x-flow-id", UUID.randomUUID().toString())
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(String.class).isEqualTo("Computer says no");
    }
}
