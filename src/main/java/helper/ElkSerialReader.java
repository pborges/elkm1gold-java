package helper;

import decoder.ElkResponseDecoderManager;
import encoder.ElkRequestEncoder;
import message.response.ElkResponse;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by pborges on 2/5/15.
 */
public class ElkSerialReader implements Runnable {
    private Logger log = Logger.getLogger(ElkSerialReader.class);

    private InputStream inputStream;
    private HashMap<Class<? extends ElkResponse>, ElkResponseProcessor<? extends ElkResponse>> elkResponseProcessorMap;
    private ElkResponseDecoderManager elkResponseDecoderManager = new ElkResponseDecoderManager();
    private ElkRequestEncoder elkRequestEncoder = new ElkRequestEncoder();

    public ElkSerialReader(InputStream inputStream, HashMap<Class<? extends ElkResponse>, ElkResponseProcessor<? extends ElkResponse>> elkResponseProcessorMap) {
        this.inputStream = inputStream;
        this.elkResponseProcessorMap = elkResponseProcessorMap;
    }

    @Override
    public void run() {
        log.debug("Starting SerialReader");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                log.debug("Read: " + line);
                ElkResponse decoded = elkResponseDecoderManager.decode(line);
                @SuppressWarnings("SuspiciousMethodCalls")
                ElkResponseProcessor elkResponseProcessor = elkResponseProcessorMap.get(decoded.getClass());
                if(elkResponseProcessor != null) {
                    log.warn("Found processor for " + decoded.getClass().getSimpleName() + " of type " + elkResponseProcessor.getClass());
                    elkResponseProcessor.processRecord(decoded);
                }else{
                    log.warn("No processor for " + decoded.getClass().getSimpleName());
                }
            }
        } catch (Exception e) {
            log.error("Unable to read line", e);
        }
    }
}
