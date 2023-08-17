package de.woezelmann.http;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ArtistService {

    @PostExchange(value = "/artists", contentType = "application/json", accept = "application/json")
    Mono<Artist> createArtist(@RequestBody Artist artist);

    @GetExchange(value = "/artists", accept = "application/json")
    Flux<Artist> listArtists();

    @GetExchange(value = "/artists/{id}", accept = "application/json")
    Mono<Artist> fetchArtist(@PathVariable("id") String id);
}
