import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

// The tests for your playlist. You don't need to change this file.
// Each message says what was expected, so a failing test tells you what to look at.
class PlaylistTest {
    private static Playlist make(String... songs) {
        Playlist playlist = new Playlist();
        for (String song : songs) {
            playlist.add(song);
        }
        return playlist;
    }

    private static List<String> songs(Playlist playlist) {
        List<String> songs = new ArrayList<>();
        for (int i = 0; i < playlist.count(); i++) {
            songs.add(playlist.get(i));
        }
        return songs;
    }

    @Test
    void addKeepsTheSongsInOrder() {
        Playlist playlist = make("Midnight Bus", "Lemon Skies", "Slow Rain");
        assertEquals(3, playlist.count(), "Not yet: three songs were added, so the count is 3.");
        assertEquals("Midnight Bus", playlist.get(0), "Not yet: the first song added is at index 0.");
        assertEquals("Slow Rain", playlist.get(2), "Not yet: the third song added is at index 2.");
    }

    @Test
    void capacityStartsAtTwoAndDoublesWhenFull() {
        Playlist playlist = new Playlist();
        assertEquals(2, playlist.capacity(), "Not yet: a new playlist has 2 slots.");
        playlist.add("Midnight Bus");
        playlist.add("Lemon Skies");
        assertEquals(2, playlist.capacity(), "Not yet: 2 songs fit in 2 slots, so there's no moving day yet.");
        playlist.add("Slow Rain");
        assertEquals(4, playlist.capacity(), "Not yet: the third song finds the array full, so the capacity doubles to 4.");
        playlist.add("Paper Boats");
        playlist.add("Neon Garden");
        assertEquals(8, playlist.capacity(), "Not yet: the fifth song finds 4 slots full, so the capacity doubles to 8.");
        assertEquals(List.of("Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats", "Neon Garden"), songs(playlist),
            "Not yet: moving day copies every song across, in order.");
    }

    @Test
    void getRefusesAnIndexWithNoSong() {
        Playlist playlist = make("Midnight Bus", "Lemon Skies", "Slow Rain");
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.get(-1), "Not yet: index -1 has no song.");
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.get(3), "Not yet: index 3 is a spare slot, not a song.");
    }

    @Test
    void insertAtTheFrontMovesEveryoneOver() {
        Playlist playlist = make("Midnight Bus", "Lemon Skies", "Slow Rain");
        playlist.insertAt(0, "Golden Hour");
        assertEquals(List.of("Golden Hour", "Midnight Bus", "Lemon Skies", "Slow Rain"), songs(playlist),
            "Not yet: Golden Hour goes first, and every other song moves one slot right.");
    }

    @Test
    void insertInTheMiddleAndAtTheEnd() {
        Playlist playlist = make("Midnight Bus", "Slow Rain");
        playlist.insertAt(1, "Lemon Skies");
        assertEquals(List.of("Midnight Bus", "Lemon Skies", "Slow Rain"), songs(playlist),
            "Not yet: Lemon Skies goes between Midnight Bus and Slow Rain.");
        playlist.insertAt(3, "Paper Boats");
        assertEquals(List.of("Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats"), songs(playlist),
            "Not yet: inserting at the count adds at the end.");
        assertEquals(4, playlist.capacity(), "Not yet: inserting into a full playlist grows it, as add does.");
    }

    @Test
    void insertRefusesAnIndexPastTheEnd() {
        Playlist playlist = make("Midnight Bus", "Lemon Skies");
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.insertAt(3, "Slow Rain"), "Not yet: with 2 songs, index 3 would leave a gap.");
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.insertAt(-1, "Slow Rain"), "Not yet: index -1 isn't a place in the playlist.");
    }

    @Test
    void removeAtReturnsTheSongAndClosesTheGap() {
        Playlist playlist = make("Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats");
        assertEquals("Lemon Skies", playlist.removeAt(1), "Not yet: removeAt hands back the song it took out.");
        assertEquals(List.of("Midnight Bus", "Slow Rain", "Paper Boats"), songs(playlist),
            "Not yet: the songs after Lemon Skies move one slot left.");
        assertEquals("Midnight Bus", playlist.removeAt(0), "Not yet: index 0 is the first song.");
        assertEquals("Paper Boats", playlist.removeAt(1), "Not yet: index 1 is now the last song.");
        assertEquals(List.of("Slow Rain"), songs(playlist), "Not yet: one song is left.");
        assertEquals(4, playlist.capacity(), "Not yet: removing doesn't change the capacity.");
    }

    @Test
    void removeAtRefusesAnIndexWithNoSong() {
        Playlist playlist = make("Midnight Bus");
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.removeAt(1), "Not yet: with 1 song, index 1 has no song.");
        playlist.removeAt(0);
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.removeAt(0), "Not yet: an empty playlist has nothing to remove.");
    }

    @Test
    void aThousandSongs() {
        Playlist playlist = new Playlist();
        for (int i = 0; i < 1000; i++) {
            playlist.add("Song " + i);
        }
        assertEquals(1000, playlist.count(), "Not yet: 1,000 songs were added.");
        assertEquals(1024, playlist.capacity(), "Not yet: doubling from 2 gives 1,024 slots for 1,000 songs.");
        assertEquals("Song 999", playlist.get(999), "Not yet: the last song is at index 999.");
    }
}
