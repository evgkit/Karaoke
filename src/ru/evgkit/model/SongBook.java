package ru.evgkit.model;

import java.util.*;

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

    public Set<String> getArtists() {
        return byArtist().keySet();
    }

    public List<Song> getSongsFoArtist(String artistName) {
        return byArtist().get(artistName);
    }

    // FIXME: This should be cached!
    private Map<String, List<Song>> byArtist() {
        Map<String, List<Song>> byArtist = new HashMap<>();

        for (Song song : mSongs) {
            List<Song> artistsSongs = byArtist.get(song.getArtist());
            if (null == artistsSongs) {
                artistsSongs = new ArrayList<>();
                byArtist.put(song.getArtist(), artistsSongs);
            }
            artistsSongs.add(song);
        }

        return byArtist;
    }
}
