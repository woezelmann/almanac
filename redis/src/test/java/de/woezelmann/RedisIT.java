package de.woezelmann;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
@Import(EmbeddedRedisConfiguration.class)

@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@ActiveProfiles("it")
public class RedisIT {

    @Autowired
    private WebTestClient client;

    @Test
    void createAndFetch() throws Exception {

        Map<String, String> created = client.post()
                .uri("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createCustomer("Hans", "Wurst"))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(new ParameterizedTypeReference<Map<String, String>>() {
                })
                .getResponseBody()
                .blockFirst();

        String customerId = created.get("id");

        Assertions.assertNotNull(customerId);
        Assertions.assertEquals(created.get("first_name"), "Hans");
        Assertions.assertEquals(created.get("last_name"), "Wurst");

        Map<String, String> fetched = client.get()
                .uri("/customer/{id}", customerId)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(new ParameterizedTypeReference<Map<String, String>>() {
                })
                .getResponseBody()
                .blockFirst();

        Assertions.assertEquals(fetched.get("id"), customerId);
        Assertions.assertEquals(fetched.get("first_name"), "Hans");
        Assertions.assertEquals(fetched.get("last_name"), "Wurst");
    }

    @Test
    void fetchNonExistingResource() {
        client.get()
                .uri("/customer/{id}", "non-existing")
                .exchange()
                .expectStatus().isNotFound();
    }

    private Map<String, String> createCustomer(String firstName, String lastName) {
        Map<String, String> map = new HashMap<>();
        map.put("first_name", firstName);
        map.put("last_name", lastName);
        return map;
    }

}
