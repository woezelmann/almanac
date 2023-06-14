package de.woezelmann;

import lombok.Getter;

@Getter
public class StorageException extends Exception {

    private int httpCode;
    public StorageException(String message, int httpCode) {
        super(message);
        this.httpCode = httpCode;
    }
}
