package ru.evgkit;

import ru.evgkit.model.Song;
import ru.evgkit.model.SongBook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

/**
 * Awesome karaoke machine
 * Created by e on 10.09.15.
 */
public class KaraokeMachine {
    private SongBook mSongBook;
    private BufferedReader mReader;
    private Queue<Song> mSongQueue;
    private Map<String, String> mMenu;

    public KaraokeMachine(SongBook songBook) {
        mSongBook = songBook;
        mSongQueue = new ArrayDeque<>();

        mReader = new BufferedReader(new InputStreamReader(System.in));

        mMenu = new HashMap<>();
        mMenu.put("add", "Add a new song to the song book");
        mMenu.put("play", "Play next song in the queue");
        mMenu.put("choose", "Choose a song");
        mMenu.put("quit", "Give up. Exit the program");
    }

    public void run() {
        String choice = "";
        do {
            try {
                choice = promptAction();

                switch (choice) {
                    case "add":
                        Song song = promptNewSong();
                        mSongBook.addSong(song);

                        System.out.printf("%s added! %n%n", song);
                        break;

                    case "play":
                        playNext();
                        break;

                    case "choose":
                        String artist = promptArtist();
                        Song artistSong = promptSongForArtist(artist);
                        mSongQueue.add(artistSong);

                        System.out.printf("You chose: %s %n", artistSong);
                        break;

                    case "quit":
                        System.out.println("Thanks for playing!");
                        break;

                    default:
                        System.out.printf("Unknown choice: '%s'. Try again. %n", choice);
                        break;
                }
            } catch(IOException ioe) {
                System.out.println("Problem with input");
                ioe.printStackTrace();
            }
        } while(!choice.equals("quit"));
    }

    public void playNext() {
        Song song = mSongQueue.poll();
        if (null != song) {
            System.out.printf("%n%n%n Open %s hear %s by %s %n%n%n",
                    song.getVideoUrl(),
                    song.getTitle(),
                    song.getArtist());
        } else {
            System.out.println("Sorry there are no songs in the queue." +
                    " Use choose from menu to add some");
        }
    }

    private String promptAction() throws IOException {
        System.out.printf("There are %d songs available and %d in the queue. Your options are: %n",
                mSongBook.getSongsCount(),
                mSongQueue.size());

        for (Map.Entry<String, String> option : mMenu.entrySet()) {
            System.out.printf("%s - %s %n",
                    option.getKey(),
                    option.getValue());
        }

        System.out.print("What do you want to do: ");
        String choice = mReader.readLine();
        return choice.trim().toLowerCase();
    }

    private Song promptNewSong() throws IOException {
        System.out.print("Enter the artist's name: ");
        String artist = mReader.readLine();

        System.out.print("Enter the title: ");
        String title = mReader.readLine();

        System.out.print("Enter the video url: ");
        String videoUrl = mReader.readLine();

        return new Song(artist, title, videoUrl);
    }

    private int promptForIndex(List<String> options) throws IOException {
        int counter = 1;

        for (String option : options) {
            System.out.printf("%d.) %s %n", counter++, option);
        }

        System.out.print("Your choice: ");
        int choice = Integer.parseInt(mReader.readLine().trim());

        return --choice;
    }

    private String promptArtist() throws IOException {
        List<String> artists = new ArrayList<>(mSongBook.getArtists());

        System.out.println("Available artists:");
        int index = promptForIndex(artists);

        return artists.get(index);
    }

    private Song promptSongForArtist(String artist) throws IOException {
        List<Song> songs = mSongBook.getSongsFoArtist(artist);
        List<String> songTitles = new ArrayList<>();

        for (Song song : songs) {
            songTitles.add(song.getTitle());
        }

        System.out.printf("Available songs for %s: %n", artist);
        int index = promptForIndex(songTitles);
        return songs.get(index);
    }
}
