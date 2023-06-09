package events.tcp;

import devices.Device;
import model.packet.transport.TcpPayload;

import java.util.ArrayList;


public class TcpSynAckEvent extends TcpEvent {

    public TcpSynAckEvent(Device source, Device destination, int sourcePort, int destinationPort) {
        super(source, destination, sourcePort, destinationPort);
    }

    @Override
    protected ArrayList<TcpPayload.Flag> getFlags() {
        ArrayList<TcpPayload.Flag> flags = new ArrayList<>();
        flags.add(TcpPayload.Flag.SYN);
        flags.add(TcpPayload.Flag.ACK);
        return flags;
    }

}
