import ru.evgkit.KaraokeMachine;
import ru.evgkit.model.SongBook;

/**
 * Mockup class for testing
 * Created by e on 10.09.15.
 */
public class Karaoke {

    public static void main(String[] args) {
        SongBook songBook = new SongBook();

        KaraokeMachine machine = new KaraokeMachine(songBook);
        machine.run();
    }
}
