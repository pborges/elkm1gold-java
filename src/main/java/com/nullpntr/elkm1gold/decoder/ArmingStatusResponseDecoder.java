package com.nullpntr.elkm1gold.decoder;

import com.nullpntr.elkm1gold.message.response.ArmingStatus;
import com.nullpntr.elkm1gold.message.response.ArmingStatusResponse;

/**
 * Created by pborges on 1/26/15.
 */
public class ArmingStatusResponseDecoder implements ElkDecoder<ArmingStatusResponse> {
    @Override
    public ArmingStatusResponse decode(String data) {
        ArmingStatusResponse armingStatusReport = new ArmingStatusResponse();
        for (int i = 0; i < 8; i++) {
            ArmingStatus armingStatus = new ArmingStatus();
            armingStatus.setArea(i);

            armingStatus.setArmingStatus(data.charAt(i + 4));
            armingStatus.setArmingSubStatus(data.charAt(i + 4));

            armingStatus.setArmUpState(data.charAt(i + 12));

            armingStatus.setAlarmState(data.charAt(i + 20));
            armingStatus.setAlarmSubState(data.charAt(i + 20));

            armingStatusReport.addArmingStatus(armingStatus);
        }
        return armingStatusReport;
    }
}
