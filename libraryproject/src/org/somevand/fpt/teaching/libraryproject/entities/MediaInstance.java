package org.somevand.fpt.teaching.libraryproject.entities;

import java.util.Objects;

public abstract class MediaInstance {

    private final int uid;
    private final Media media;

    public MediaInstance(int uid, Media media) {
        this.uid = uid;
        this.media = Objects.requireNonNull(media);
    }

    public int getUid() {
        return uid;
    }

    public Media getMedia() {
        return media;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MediaInstance other && uid == other.uid;
    }

    @Override
    public int hashCode() {
        return uid;
    }

    @Override
    public String toString() {
        return "MediaInstance{" +
                "uid=" + uid +
                ", media=" + media +
                '}';
    }
}
