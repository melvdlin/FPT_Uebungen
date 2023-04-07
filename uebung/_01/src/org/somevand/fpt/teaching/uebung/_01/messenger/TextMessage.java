package org.somevand.fpt.teaching.uebung._01.messenger;

public class TextMessage {
    private final Contact sender, recipient;
    private final String body;
    private final long timestamp;

    public TextMessage(Contact sender, Contact recipient, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.body      = body;
        this.timestamp = System.currentTimeMillis();
    }

    public Contact getSender() {
        return sender;
    }

    public Contact getRecipient() {
        return recipient;
    }

    public String getBody() {
        return body;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
