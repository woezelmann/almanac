package de.woezelmann.swagger;

import lombok.Getter;

@Getter
public class CustomerException extends RuntimeException {

    public int httpStatus;

    public CustomerException(String message, int httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
