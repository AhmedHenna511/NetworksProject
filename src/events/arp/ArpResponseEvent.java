package events.arp;

import devices.Device;
import events.Event;
import model.packet.IpPayload;
import model.packet.Packet;

public class ArpResponseEvent extends Event {

    public ArpResponseEvent(Device source, Device destination) {
        super(source, destination);
    }

    @Override
    public Packet createPacket() {
        IpPayload ipPayload = new IpPayload(destination.getIpAddress(), source.getIpAddress(), null);
        return new Packet(destination.getMacAddress(), source.getMacAddress(), ipPayload);
    }
}
