package com.yingwu.digital.base;


public class DigitalApiException extends Exception {

    public DigitalApiException() {

    }

    public DigitalApiException(DigitalApiError error) {
        super(error.toString());
    }

    public DigitalApiException(Throwable cause) {
        super(cause);
    }

    public DigitalApiException(String msg) {
        super(msg);
    }

    public DigitalApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
