package ru.evgkit.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Song helper
 * Created by e on 10.09.15.
 */
public class SongBook {
    private List<Song> mSongs;

    public SongBook() {
        mSongs = new ArrayList<>();
    }

    public void addSong(Song song) {
        mSongs.add(song);
    }

    public int getSongsCount() {
        return mSongs.size();
    }
}
