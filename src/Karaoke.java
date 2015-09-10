import ru.evgkit.model.Song;
import ru.evgkit.model.SongBook;

/**
 * Mockup class for testing
 * Created by e on 10.09.15.
 */
public class Karaoke {

    public static void main(String[] args) {
        Song song = new Song(
                "Michael Jackson",
                "Beat It",
                "http://www.youtube.com/watch?v=oRdxUFDoQe0"
        );

        SongBook songBook = new SongBook();

        System.out.printf("Adding %s %n", song);
        songBook.addSong(song);

        System.out.printf("There are %d songs. %n", songBook.getSongsCount());
    }
}
