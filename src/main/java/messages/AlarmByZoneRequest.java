package messages;

/**
 * Created by pborges on 1/22/15.
 */
public class AlarmByZoneRequest implements ElkRequest {
    public String getPayload() {
        return "az00";
    }
}
