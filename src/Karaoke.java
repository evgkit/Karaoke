import ru.evgkit.KaraokeMachine;
import ru.evgkit.model.SongBook;

/**
 * Mockup class for testing
 * Created by e on 10.09.15.
 */
public class Karaoke {

    public static void main(String[] args) {
        SongBook songBook = new SongBook();

        String fileName = "songs.txt";
        songBook.importFrom(fileName);

        KaraokeMachine machine = new KaraokeMachine(songBook);
        machine.run();

        System.out.println("Saving book ...");
        songBook.exportTo(fileName);
    }
}
