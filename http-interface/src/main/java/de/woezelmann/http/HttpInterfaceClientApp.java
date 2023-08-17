package de.woezelmann.http;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class HttpInterfaceClientApp {

    public static void main(String[] args) {
        SpringApplication.run(HttpInterfaceClientApp.class, args);
    }

    @Bean
    public ArtistService artistService(@Value("${urls.artist-service}") String url) {
        WebClient client = WebClient.builder().baseUrl(url).build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(ArtistService.class);
    }
}
