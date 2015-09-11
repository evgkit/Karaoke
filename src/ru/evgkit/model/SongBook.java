package ru.evgkit.model;

import java.io.*;
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

    public void exportTo(String fileName) {
        try (
                FileOutputStream fos = new FileOutputStream(fileName);
                PrintWriter writer = new PrintWriter(fos)
        ) {
            for (Song song : mSongs) {
                writer.printf("%s|%s|%s%n",
                        song.getArtist(),
                        song.getTitle(),
                        song.getVideoUrl());
            }
        } catch (IOException ioe) {
            System.out.printf("Problem saving the file %s %n", fileName);
            ioe.printStackTrace();
        }
    }

    public void importFrom(String fileName) {
        try (
                FileInputStream fis = new FileInputStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis))
        ) {
            String line;
            while (null != (line = reader.readLine())) {
                String[] args = line.split("\\|");
                addSong(new Song(args[0], args[1], args[2]));
            }
        } catch (IOException ioe) {
            System.out.printf("Problem loading the file %s %n", fileName);
            ioe.printStackTrace();
        }
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
        List<Song> songs = byArtist().get(artistName);
        songs.sort((song1, song2) -> {
            if (song1.equals(song2)) {
                return 0;
            }
            return song1.mTitle.compareTo(song2.mTitle);
        });

        return songs;
    }

    // FIXME: This should be cached!
    private Map<String, List<Song>> byArtist() {
        Map<String, List<Song>> byArtist = new TreeMap<>();

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
