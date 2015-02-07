package com.nullpntr.elkm1gold.decoder;

import com.nullpntr.elkm1gold.message.response.ZoneChangeUpdate;
import org.apache.log4j.Logger;

import java.util.HashMap;


/**
 * Created by pborges on 1/22/15.
 */
public class ZoneChangeUpdateDecoder implements ElkDecoder<ZoneChangeUpdate> {
    Logger log = Logger.getLogger(ZoneChangeUpdateDecoder.class);
    HashMap<Integer,ZoneChangeUpdate.ZoneStatus> zoneStatusHashMap = new HashMap<>();
    HashMap<Integer,ZoneChangeUpdate.ZoneSubStatus> zoneSubStatusHashMap = new HashMap<>();

    public ZoneChangeUpdateDecoder() {
        zoneStatusHashMap.put(0, ZoneChangeUpdate.ZoneStatus.NORMAL);
        zoneStatusHashMap.put(1, ZoneChangeUpdate.ZoneStatus.TROUBLE);
        zoneStatusHashMap.put(2, ZoneChangeUpdate.ZoneStatus.VIOLATED);
        zoneStatusHashMap.put(3, ZoneChangeUpdate.ZoneStatus.BYPASSED);

        zoneSubStatusHashMap.put(0, ZoneChangeUpdate.ZoneSubStatus.UNCONFIGURED);
        zoneSubStatusHashMap.put(1, ZoneChangeUpdate.ZoneSubStatus.OPEN);
        zoneSubStatusHashMap.put(2, ZoneChangeUpdate.ZoneSubStatus.EOL);
        zoneSubStatusHashMap.put(3, ZoneChangeUpdate.ZoneSubStatus.SHORT);
    }

    public ZoneChangeUpdate decode(String data) {
        ZoneChangeUpdate zoneChangeUpdate = new ZoneChangeUpdate();
        zoneChangeUpdate.setZoneNumber(Integer.parseInt(data.substring(4, 7)));
        int zoneStatus = (data.charAt(7) >> 4) & 0xF;
//        log.debug("ZoneStatus: " + zoneStatus);
        zoneChangeUpdate.setZoneStatus(zoneStatusHashMap.get(zoneStatus));

        int zoneSubStatus = data.charAt(7) & 0xF;
//        log.debug("ZoneSubStatus: " + zoneSubStatus);
        zoneChangeUpdate.setZoneSubStatus(zoneSubStatusHashMap.get(zoneSubStatus));

        return zoneChangeUpdate;
    }
}
