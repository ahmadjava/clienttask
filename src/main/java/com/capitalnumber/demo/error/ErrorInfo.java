package com.capitalnumber.demo.error;

public class ErrorInfo {
    public final String url;
    public final String message;
    public final Integer code;

    public ErrorInfo(String url, Exception ex, Integer code) {
        this.url = url;
        this.message = ex.getLocalizedMessage();
        this.code = code;
    }

}
