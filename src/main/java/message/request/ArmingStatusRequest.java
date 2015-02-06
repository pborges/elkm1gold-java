package message.request;

/**
 * Created by pborges on 1/22/15.
 */
public class ArmingStatusRequest implements ElkRequest {
    public String getPayload() {
        return "as00";
    }
}
