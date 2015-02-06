import encoder.ElkRequestEncoder;
import gnu.io.*;
import helper.ElkResponseProcessor;
import helper.ElkSerialReader;
import messages.ElkRequest;
import messages.ElkResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pborges on 2/5/15.
 */
public class ElkModem {
    private Logger log = Logger.getLogger(ElkModem.class);

    private SerialPort serialPort;
    private ElkRequestEncoder elkRequestEncoder = new ElkRequestEncoder();
    private OutputStreamWriter outputStreamWriter;
    private HashMap<Class<? extends ElkResponse>, ElkResponseProcessor<? extends ElkResponse>> elkResponseProcessorMap;

    public ElkModem(String serialPortName, Integer baud, HashMap<Class<? extends ElkResponse>, ElkResponseProcessor<? extends ElkResponse>> elkResponseProcessorMap) throws PortInUseException, NoSuchPortException, UnsupportedCommOperationException, IOException {
        log.debug("ElkModem(" + serialPortName + ", " + baud + ")");
        for (Map.Entry<Class<? extends ElkResponse>, ElkResponseProcessor<? extends ElkResponse>> entry : elkResponseProcessorMap.entrySet()) {
            log.debug("\tLoading " + entry.getKey().getSimpleName() + " processor " + entry.getValue().getClass().getSimpleName());
        }

        this.elkResponseProcessorMap = elkResponseProcessorMap;
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(serialPortName);
        CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);
        serialPort = (SerialPort) commPort;
        serialPort.setSerialPortParams(baud, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        serialPort.setDTR(false);
        outputStreamWriter = new OutputStreamWriter(serialPort.getOutputStream());
    }

    public void listen() throws IOException {
        ElkSerialReader elkSerialReader = new ElkSerialReader(serialPort.getInputStream(), elkResponseProcessorMap);
        new Thread(elkSerialReader).start();
    }

    public void sendRequest(Class<? extends ElkRequest> requestClass) throws IllegalAccessException, InstantiationException, IOException {
        outputStreamWriter.write(elkRequestEncoder.encode(requestClass.newInstance()));
    }
}
