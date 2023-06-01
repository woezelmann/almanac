package de.woezelmann;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class ReverseIT {

    @Autowired
    private WebTestClient client;

    @Test
    void testReversion() throws Exception {
        client.post()
                .uri("/aop/reverse")
                .contentType(MediaType.TEXT_PLAIN)
                .bodyValue("I'll be back")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).isEqualTo("kcab eb ll'I");
    }

    @Test
    void testReversionWithAspect() throws Exception {
        client.post()
                .uri("/aop/reverse-with-aspect")
                .contentType(MediaType.TEXT_PLAIN)
                .bodyValue("I'll be back")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).isEqualTo("KCAB EB LL'I");
    }
}
