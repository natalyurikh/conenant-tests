package com.natalya.challenge.covenant.domain.listener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class BridgeListener extends Listener {

    private String connectAddress;
    private BridgeProfileType profileType;

    @AllArgsConstructor
    @Getter
    public enum BridgeProfileType {
        TCP("TCPBridgeProfile"),
        DEFAULT("DefaultBridgeProfile");

        private final String description;
    }

    @Builder
    public BridgeListener(String name, String bindAddress, String bindPort, String connectPort,
            String connectAddress, BridgeProfileType profileType) {
        super(name, bindAddress, bindPort, connectPort);
        this.connectAddress = connectAddress;
        this.profileType = profileType;
    }

}
