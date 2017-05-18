package com.tianma.service.websocket;

/**
 * Created by jie on 17-2-19.
 */
public class EchoServiceImpl implements EchoService {
    private final String echoFormat;

    public EchoServiceImpl(String echoFormat) {
        this.echoFormat = (echoFormat != null) ? echoFormat : "%s";
    }

    public String getMessage(String message) {
        return String.format(this.echoFormat, message);
    }
}