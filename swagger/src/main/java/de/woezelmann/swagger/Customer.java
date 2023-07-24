package de.woezelmann.swagger;

import java.util.Date;

public record Customer(
        String id,
        String firstname,
        String lastname,
        Date registrationDate) {

    public Customer withId(String id) {
        return new Customer(id, firstname(), lastname(), registrationDate());
    }
}
