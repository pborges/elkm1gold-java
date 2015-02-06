package encoder;

import message.request.ElkRequest;

/**
 * Created by pborges on 2/5/15.
 */
public class ElkRequestEncoder {
    public String encode(ElkRequest elkRequest) {
        return checksumMessage(elkRequest.getPayload());
    }

    private String hex2(int i) {
        String h = Integer.toHexString(i).toUpperCase();
        if (h.length() == 1) {
            h = '0' + h;
        }
        return h;
    }

    private String checksumMessage(String cmd) {
        String fullCmd = hex2(cmd.length() + 2) + cmd;
        byte[] bytes = fullCmd.getBytes();
        int sum = 0;
        for (byte aByte : bytes) {
            sum = sum + aByte;
        }
        int cc = (((sum & 0xff) ^ 0xff) + 1) & 0xff;
        fullCmd = fullCmd + hex2(cc);
        return fullCmd;
    }

}
