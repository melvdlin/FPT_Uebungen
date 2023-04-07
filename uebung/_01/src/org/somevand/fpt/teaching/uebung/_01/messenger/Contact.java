package org.somevand.fpt.teaching.uebung._01.messenger;

import java.io.IOException;
import java.net.InetAddress;

public class Contact {

    private final String name;
    private final InetAddress address;

    public Contact(String name, InetAddress address) {
        this.name    = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void message(Contact sender, String text) throws IOException {
        TextMessage message = new TextMessage(sender, this, text);
        NetworkHelper.sendMessage(message);
    }
}
