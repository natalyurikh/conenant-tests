package com.natalya.challenge.covenant.domain.listener;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
public class HttpListener extends Listener {

    private Map<String, String> connectAddresses;
    private boolean useSsl;
    private HttpProfileType profileType;

    public enum HttpProfileType {
        CUSTOM,
        DEFAULT;
    }

    @Builder
    public HttpListener(String name, String bindAddress, String bindPort, String connectPort,
            Map<String, String> connectAddresses, boolean useSsl, HttpProfileType profileType) {
        super(name, bindAddress, bindPort, connectPort);
        this.connectAddresses = connectAddresses;
        this.useSsl = useSsl;
        this.profileType = profileType;
    }
}
