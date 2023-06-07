package org.somevand.fpt.teaching.libraryproject.entities;

import org.somevand.fpt.teaching.libraryproject.entities.Author;
import org.somevand.fpt.teaching.libraryproject.entities.Media;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Video extends Media {
    private final Duration duration;

    public Video(int uid, String title, List<Author> authors, LocalDateTime released, Duration duration) {
        super(uid, title, authors, released);
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "org.somevand.fpt.teaching.libraryproject.entities.Video{" +
                "duration=" + duration +
                "} " + super.toString();
    }
}
