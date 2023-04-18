package events.tcp;

import model.Device;
import model.packet.IpPayload;
import model.packet.Packet;
import model.packet.transport.TcpPayload;

import java.util.ArrayList;

abstract class TcpDataEvent extends TcpEvent {
    private final byte[] data;
    private final int sequenceNumber;
    private final int acknowledgmentNumber;

    public TcpDataEvent(Device source, Device destination, byte[] data, int sourcePort, int destinationPort, int sequenceNumber, int acknowledgmentNumber) {
        super(source, destination, sourcePort, destinationPort);
        this.data = data;
        this.sequenceNumber = sequenceNumber;
        this.acknowledgmentNumber = acknowledgmentNumber;
    }

    @Override
    public Packet createPacket() {
        TcpPayload tcpPayload = new TcpPayload(sourcePort, destinationPort, null, sequenceNumber, acknowledgmentNumber, flags);
        IpPayload ipPayload = new IpPayload(destination.getIpAddress(), source.getIpAddress(), tcpPayload);
        return new Packet(destination.getMacAddress(), source.getMacAddress(), ipPayload);
    }

    @Override
    protected ArrayList<TcpPayload.Flag> getFlags() {
        return new ArrayList<>();
    }

    public byte[] getData() {
        return data;
    }
}