package org.somevand.fpt.teaching.libraryproject.entities;

import java.util.Objects;

public class PhysicalMediaInstance extends MediaInstance {
    private final Location location;

    public PhysicalMediaInstance(int uid, Media media, Location location) {
        super(uid, media);
        this.location = Objects.requireNonNull(location);
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "PhysicalMediaInstance{" +
                "location=" + location +
                "} " + super.toString();
    }
}
