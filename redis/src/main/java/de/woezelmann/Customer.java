package de.woezelmann;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Customer(
        @JsonProperty("id") String id,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName) {
}
