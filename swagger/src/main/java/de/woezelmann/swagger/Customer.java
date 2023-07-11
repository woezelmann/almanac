package de.woezelmann.swagger;

public record Customer(
        String id,
        String firstname,
        String lastname) {

    public Customer withId(String id) {
        return new Customer(id, firstname(), lastname());
    }
}
