package org.example.csis231_fall27_2.api;

public class ApiException extends RuntimeException{
    private final int status;
    public ApiException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
