package model.packet;

import model.IpAddress;
import model.packet.transport.TransportPayload;

public class IpPayload {
    private final IpAddress destinationIp;
    private final IpAddress sourceIp;
    private final TransportPayload transportPayload;

    public IpPayload(IpAddress destinationIp, IpAddress sourceIp, TransportPayload transportPayload) {
        this.destinationIp = destinationIp;
        this.sourceIp = sourceIp;
        this.transportPayload = transportPayload;
    }

    public IpAddress getDestinationIp() {
        return destinationIp;
    }

    public IpAddress getSourceIp() {
        return sourceIp;
    }

    public TransportPayload getTransportPayload() {
        return transportPayload;
    }
}
