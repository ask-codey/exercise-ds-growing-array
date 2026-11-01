import java.util.ArrayList;
import java.util.List;

// Tries your playlist. Run it with: java src/main/java/Demo.java
public class Demo {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        for (String song : new String[] {"Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats", "Neon Garden"}) {
            playlist.add(song);
        }
        System.out.println("Added: " + songs(playlist));
        System.out.println("Count: " + playlist.count() + ", capacity: " + playlist.capacity());
        playlist.insertAt(0, "Golden Hour");
        System.out.println("Inserted Golden Hour at 0: " + songs(playlist));
        System.out.println("Removed index 2: " + playlist.removeAt(2));
        System.out.println("Now: " + songs(playlist));
        System.out.println("Index 3: " + playlist.get(3));
    }

    static String songs(Playlist playlist) {
        List<String> songs = new ArrayList<>();
        for (int i = 0; i < playlist.count(); i++) {
            songs.add(playlist.get(i));
        }
        return String.join(", ", songs);
    }
}
