package devices;

import events.Event;
import events.EventWithDirectSourceDestination;
import events.OnEvent;
import model.IpAddress;
import model.Link;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class Switch extends Device {

    private final ArrayList<Link> linkedDevices = new ArrayList<>();

    public Switch(String name, String macAddress, IpAddress ipAddress, IpAddress subnetMask, Device defaultGateway,
                  BlockingQueue<EventWithDirectSourceDestination> eventQueue
    ) {
        super(name, macAddress, ipAddress, subnetMask, defaultGateway, null, eventQueue);
    }

    @Override
    public void sendEvent(Event event) {
        System.err.println("sendEvent unimplemented in Switch, use sendSwitchEvent");
    }


    @Override
    public boolean processSentEvent(Device destination, Event event) {
        logSentEvent(event, destination);
        for (OnEvent onSentEvent : onSentEventListeners) {
            onSentEvent.onEvent(event);
        }
        return true;
    }

    @Override
    public void processReceivedEvent(Device source, Event event) {
        pauseBeforeReceiving();
        logReceivedEvent(event, source);
        for (OnEvent onReceivedEvent : onReceivedEventListeners) {
            onReceivedEvent.onEvent(event);
        }
        if (event.getPacket().getDestinationMacAddress().equals("ff:ff:ff:ff:ff:ff")) {
            sendToAll(event, source);
        } else {
            sendSwitchEvent(event, source);
        }
    }

    private void sendSwitchEvent(Event event, Device eventSource) {
        pauseBeforeSending(event);
        Device destination = event.getDestination();
        boolean isLinkedToDest = false;

        for (Link link : linkedDevices) {
            if (link.getLinkedDevice() == destination) {
                isLinkedToDest = true;
                break;
            }
        }
        if (isLinkedToDest) {
            processSentEvent(destination, event);
            sendEventToDevice(destination, event);
        } else {
            sendToAll(event, eventSource);
        }
    }

    private void sendToAll(Event event, Device eventSource) {
        for (Link link : linkedDevices) {
            if (link.getLinkedDevice() != eventSource) {
                processSentEvent(link.getLinkedDevice(), event);
                sendEventToDevice(link.getLinkedDevice(), event);
            }
        }
    }


    public void addLinkedDevice(Device device) {
        this.linkedDevices.add(new Link(device, 0));
    }
}
