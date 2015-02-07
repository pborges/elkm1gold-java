package com.nullpntr.elkm1gold.message.request;

/**
 * Created by pborges on 1/22/15.
 */
public class AlarmByZoneRequest implements ElkRequest {
    public String getPayload() {
        return "az00";
    }
}
