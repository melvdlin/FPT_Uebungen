package org.somevand.fpt.teaching.libraryproject.entities;

import java.net.URL;
import java.util.Objects;

public class DigitalMediaInstance extends MediaInstance {

    private final URL url;

    public DigitalMediaInstance(int uid, Media media, URL url) {
        super(uid, media);
        this.url = Objects.requireNonNull(url);
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "DigitalMediaInstance{" +
                "url=" + url +
                "} " + super.toString();
    }
}
