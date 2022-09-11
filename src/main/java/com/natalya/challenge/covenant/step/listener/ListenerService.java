package com.natalya.challenge.covenant.step.listener;

import com.natalya.challenge.covenant.domain.listener.BridgeListener;
import com.natalya.challenge.covenant.pageobject.DashBoardPage;

public class ListenerService {

    public BridgeListener createListener(BridgeListener listener) {
        new DashBoardPage().navigation()
                .openListenersViewPage()
                .clickCreate()
                .switchBridgeTab()
                .enterName(listener.getName())
                .enterBindAddress(listener.getBindAddress())
                .enterBindPort(listener.getBindPort())
                .enterConnectPort(listener.getConnectPort())
                .enterConnectAddress(listener.getConnectAddress())
                .selectBridgeProfile(listener.getProfileType().getDescription())
                .clickCreateButton()
                .waitForReadiness();
        return listener;
    }

}
