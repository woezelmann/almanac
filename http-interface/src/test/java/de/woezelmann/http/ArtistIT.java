package de.woezelmann.http;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import wiremock.com.google.common.collect.Lists;

import java.util.List;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("it")
public class ArtistIT {

    @Autowired
    private ArtistService artistService;
    private final String artistJson = """
                {
                   "name": "Rise Against",
                   "label": "Interscope Records"
                 }""";;

    @Test
    void fetchArtist() {
        WireMock.stubFor(
                WireMock.get(WireMock.urlPathEqualTo("/artist-service/artists/123"))
                        .willReturn(ResponseDefinitionBuilder.responseDefinition()
                                .withStatus(200)
                                .withHeader("content-type", "application/json")
                                .withBody(artistJson)));

        Artist artist = artistService.fetchArtist("123").block();
        System.out.println(artist);
    }

    @Test
    void createArtist() {

        WireMock.stubFor(
                WireMock.post(WireMock.urlPathEqualTo("/artist-service/artists"))
                        .willReturn(WireMock.created()));

        artistService.createArtist(new Artist("Rise Against", "Interscope Records")).block();

        WireMock.verify(WireMock.postRequestedFor(WireMock.urlPathEqualTo("/artist-service/artists")
                ).withRequestBody(WireMock.equalToJson(artistJson)));

    }

    @Test
    void listArtists() {
        WireMock.stubFor(
                WireMock.get(WireMock.urlPathEqualTo("/artist-service/artists"))
                        .willReturn(ResponseDefinitionBuilder.okForJson(Lists.newArrayList(
                                new Artist("Pink Floyd", "Harvest Records"),
                                new Artist("Rise Against", "Interscope Records")
                        )))
        );

        List<Artist> artistList = artistService.listArtists().collectList().block();

        Assertions.assertEquals(artistList.get(0).name(), "Pink Floyd");
        Assertions.assertEquals(artistList.get(1).name(), "Rise Against");
    }
}
