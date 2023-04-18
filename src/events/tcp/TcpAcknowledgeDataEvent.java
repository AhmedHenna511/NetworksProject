package events.tcp;

import model.Device;

public class TcpAcknowledgeDataEvent extends TcpDataEvent{
    public TcpAcknowledgeDataEvent(Device source, Device destination, int sourcePort, int destinationPort, int acknowledgmentNumber) {
        super(source, destination, null, sourcePort, destinationPort, 0, acknowledgmentNumber);
    }
}