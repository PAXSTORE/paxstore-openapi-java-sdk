package com.pax.market.api.sdk.java.api.exception;

public class GatewayException extends Exception{
    private int responseCode;
    public GatewayException(int responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

    public int getResponseCode(){
        return responseCode;
    }
}
