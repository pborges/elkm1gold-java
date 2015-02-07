package com.nullpntr.elkm1gold.decoder;

import com.google.gson.Gson;
import com.nullpntr.elkm1gold.message.response.ElkResponse;
import org.apache.log4j.Logger;

import java.util.HashMap;

/**
 * Created by pborges on 2/5/15.
 */
public class ElkResponseDecoderManager {
    private Logger log = Logger.getLogger(ElkResponseDecoderManager.class);
    private Gson gson = new Gson();

    private HashMap<String, ElkDecoder<? extends ElkResponse>> decoderHashMap = new HashMap<>();
    public ElkResponseDecoderManager(){
        decoderHashMap.put("ZC", new ZoneChangeUpdateDecoder());
        decoderHashMap.put("AS", new ArmingStatusResponseDecoder());
    }
    public ElkResponse decode(String message) throws Exception{
        String messageType = message.substring(2, 4);
        ElkDecoder decoder = decoderHashMap.get(messageType);
        if (decoder != null) {
            log.debug("Found message decoder for " + decoder.getClass().getSimpleName());
            ElkResponse decoded = decoder.decode(message);
            log.debug(gson.toJson(decoded));
            return decoded;
        } else {
            log.debug("Could not find decoder for " + messageType);
            return null;
        }
    }
}
