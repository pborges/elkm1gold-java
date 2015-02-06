package message.response;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pborges on 1/22/15.
 */
public class ArmingStatusResponse implements ElkResponse {
    private HashMap<Integer, ArmingStatus> armingStatusList = new HashMap<Integer, ArmingStatus>();

    public ArmingStatus getArmingStatusByArea(int area) {
        return armingStatusList.get(area);
    }

    public List<ArmingStatus> getArmingStatusList() {
        return (List<ArmingStatus>) armingStatusList.values();
    }

    public void addArmingStatus(ArmingStatus armingStatus) {
        this.armingStatusList.put(armingStatus.getArea(), armingStatus);
    }
}
