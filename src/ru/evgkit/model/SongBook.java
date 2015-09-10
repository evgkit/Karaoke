package ru.evgkit.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Song helper
 * Created by e on 10.09.15.
 */
public class SongBook {
    private List<Song> songs;

    public SongBook() {
        songs = new ArrayList<Song>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public int getSongsCount() {
        return songs.size();
    }
}
