package events.tcp;

import model.Device;

public class TcpSendDateEvent extends TcpDataEvent {
    public TcpSendDateEvent(Device source, Device destination, byte[] data, int sourcePort, int destinationPort, int sequenceNumber, String checksum) {
        super(source, destination, data, sourcePort, destinationPort, sequenceNumber, 0, checksum);
    }
}
