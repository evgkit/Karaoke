package ru.evgkit.model;

/**
 * Song model
 * Created by e on 10.09.15.
 */
public class Song {
    private String title;
    private String artist;
    private String videoUrl;

    public Song(String artist, String title, String videoUrl) {
        this.artist = artist;
        this.title = title;
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    @Override
    public String toString() {
        return String.format("Song: %s by %s", title, artist);
    }
}
