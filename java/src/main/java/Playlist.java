// A playlist built on a plain array that grows: your four methods are
// add, get, insertAt and removeAt. The comment above each one says what
// it should do, with examples. Replace each method's body with your own
// code, and write its Big-O in its comment. Keep the songs in the slots
// array: don't use ArrayList, which would do the growing for you.
public class Playlist {
    private String[] slots = new String[2];
    private int count = 0;

    // How many songs the playlist holds.
    public int count() {
        return count;
    }

    // How many slots the array has: the most songs it holds before it grows.
    public int capacity() {
        return slots.length;
    }

    /*
     * Put the song in the next free slot, after the last song. If every
     * slot is full (count() equals capacity()), first make a new array twice the
     * size, copy every song across in order, and use the new array from then on.
     *
     * On a new playlist: add("Midnight Bus"), add("Lemon Skies") -> capacity 2
     * then add("Slow Rain") -> capacity 4, songs: Midnight Bus, Lemon Skies, Slow Rain
     *
     * Big-O: (write it here)
     */
    public void add(String song) {
        // Your code here.
    }

    /*
     * Return the song at this index, counting from 0. An index below 0, or
     * at or past the count, has no song: throw an IndexOutOfBoundsException.
     * A spare slot isn't a song, even though the slot exists.
     *
     * With Midnight Bus, Lemon Skies, Slow Rain: get(1) -> "Lemon Skies"
     * get(3) -> IndexOutOfBoundsException
     *
     * Big-O: (write it here)
     */
    public String get(int index) {
        return "";
    }

    /*
     * Put the song at this index, and move every song from that index
     * onward one slot to the right, starting from the last song so nothing
     * is overwritten. Index 0 is the front; an index equal to the count adds
     * at the end. Grow first if every slot is full, as add does. Any other
     * index throws an IndexOutOfBoundsException.
     *
     * With Midnight Bus, Slow Rain: insertAt(1, "Lemon Skies")
     * -> Midnight Bus, Lemon Skies, Slow Rain
     *
     * Big-O: (write it here)
     */
    public void insertAt(int index, String song) {
        // Your code here.
    }

    /*
     * Take out the song at this index and return it. Move every song after
     * it one slot to the left, to close the gap. The capacity stays the same.
     * An index with no song throws an IndexOutOfBoundsException.
     *
     * With Midnight Bus, Lemon Skies, Slow Rain: removeAt(0) -> "Midnight Bus",
     * and the playlist is Lemon Skies, Slow Rain
     *
     * Big-O: (write it here)
     */
    public String removeAt(int index) {
        return "";
    }
}
