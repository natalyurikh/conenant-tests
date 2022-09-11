package com.natalya.challenge.covenant.domain.launcher;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstallUtilLauncher {

    private String listener;

    private String implantTemplate;

    private String dotNetVersion;

    private boolean isValidateCert;

    private boolean isUseCertPinning;

    private String delay;

    private String jitterPercent;

    private String connectAttempts;

    private String killDateInput;

}
