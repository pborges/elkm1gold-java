package com.nullpntr.elkm1gold;

import com.nullpntr.elkm1gold.encoder.ElkRequestEncoder;
import com.nullpntr.elkm1gold.exception.AlreadyStartedException;
import gnu.io.*;
import com.nullpntr.elkm1gold.helper.ElkResponseProcessor;
import com.nullpntr.elkm1gold.helper.ElkSerialReader;
import com.nullpntr.elkm1gold.message.request.ElkRequest;
import com.nullpntr.elkm1gold.message.response.ElkResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/**
 * Created by pborges on 2/5/15.
 */
public class ElkModem {
    private Logger log = Logger.getLogger(ElkModem.class);

    private SerialPort serialPort;
    private ElkRequestEncoder elkRequestEncoder = new ElkRequestEncoder();
    private OutputStreamWriter outputStreamWriter;
    private HashMap<Class<? extends ElkResponse>, ElkResponseProcessor<? extends ElkResponse>> elkResponseProcessorMap = new HashMap<>();
    private boolean isStarted = false;

    public ElkModem(String serialPortName, Integer baud) throws PortInUseException, NoSuchPortException, UnsupportedCommOperationException, IOException {
        log.debug("ElkModem(" + serialPortName + ", " + baud + ")");

        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(serialPortName);
        CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);
        serialPort = (SerialPort) commPort;
        serialPort.setSerialPortParams(baud, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        serialPort.setDTR(false);
        outputStreamWriter = new OutputStreamWriter(serialPort.getOutputStream());
    }

    public void addListener(Class<? extends ElkResponse> clazz, ElkResponseProcessor<? extends ElkResponse> processor) throws AlreadyStartedException {
        log.debug("Loading " + clazz.getSimpleName() + " processor " + processor.getClass().getSimpleName());
        if (isStarted) {
            throw new AlreadyStartedException();
        }
        elkResponseProcessorMap.put(clazz, processor);
    }

    public void listen() throws IOException {
        log.debug("Starting listener");
        isStarted = true;
        ElkSerialReader elkSerialReader = new ElkSerialReader(serialPort.getInputStream(), elkResponseProcessorMap);
        new Thread(elkSerialReader).start();
    }

    public void sendRequest(Class<? extends ElkRequest> requestClazz) throws IllegalAccessException, InstantiationException, IOException {
        log.debug("sendRequest(" + requestClazz.getSimpleName() + ")");
        String encoded = elkRequestEncoder.encode(requestClazz.newInstance());
        log.debug("Write: " + encoded);
        outputStreamWriter.write(encoded);
        outputStreamWriter.write("\r\n");
        outputStreamWriter.flush();
    }
}
