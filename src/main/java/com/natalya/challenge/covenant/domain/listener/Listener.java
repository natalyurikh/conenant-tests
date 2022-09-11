package com.natalya.challenge.covenant.domain.listener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public abstract class Listener {

    protected String name;
    protected String bindAddress;
    protected String bindPort;
    protected String connectPort;

}
