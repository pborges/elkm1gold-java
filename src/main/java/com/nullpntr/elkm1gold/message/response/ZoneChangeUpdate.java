package com.nullpntr.elkm1gold.message.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pborges on 1/22/15.
 */
public class ZoneChangeUpdate implements ElkResponse {
    public enum ZoneStatus {
        @SerializedName("Normal")
        NORMAL,
        @SerializedName("Trouble")
        TROUBLE,
        @SerializedName("Violated")
        VIOLATED,
        @SerializedName("Bypassed")
        BYPASSED
    }

    public enum ZoneSubStatus {
        @SerializedName("Unconfigured")
        UNCONFIGURED,
        @SerializedName("Open")
        OPEN,
        @SerializedName("EOL")
        EOL,
        @SerializedName("Short")
        SHORT
    }

    private ZoneStatus zoneStatus;
    private Integer zoneNumber;
    private ZoneSubStatus zoneSubStatus;

    public ZoneStatus getZoneStatus() {
        return zoneStatus;
    }

    public void setZoneStatus(ZoneStatus zoneStatus) {
        this.zoneStatus = zoneStatus;
    }

    public Integer getZoneNumber() {
        return zoneNumber;
    }

    public void setZoneNumber(Integer zoneNumber) {
        this.zoneNumber = zoneNumber;
    }

    public ZoneSubStatus getZoneSubStatus() {
        return zoneSubStatus;
    }

    public void setZoneSubStatus(ZoneSubStatus zoneSubStatus) {
        this.zoneSubStatus = zoneSubStatus;
    }
}
