# A playlist built on a plain array that grows: your four methods are
# add, get, insert_at and remove_at. The comment inside each one says what
# it should do, with examples. Replace each method's body with your own
# code, and write its Big-O in its comment. Keep the songs in self._slots,
# a list of fixed size used as a plain array: don't call append, insert or
# pop on it, which would let Python do the growing for you.


class Playlist:
    def __init__(self):
        self._slots = [None] * 2
        self._count = 0

    @property
    def count(self):
        """How many songs the playlist holds."""
        return self._count

    @property
    def capacity(self):
        """How many slots the array has: the most songs it holds before it grows."""
        return len(self._slots)

    def add(self, song):
        """
        Put the song in the next free slot, after the last song. If every
        slot is full (count equals capacity), first make a new array twice the
        size, copy every song across in order, and use the new array from then on.

        On a new playlist: add("Midnight Bus"), add("Lemon Skies") -> capacity 2
        then add("Slow Rain") -> capacity 4, songs: Midnight Bus, Lemon Skies, Slow Rain

        Big-O: (write it here)
        """
        pass  # your code here

    def get(self, index):
        """
        Return the song at this index, counting from 0. An index below 0, or
        at or past the count, has no song: raise an IndexError.
        A spare slot isn't a song, even though the slot exists.

        With Midnight Bus, Lemon Skies, Slow Rain: get(1) -> "Lemon Skies"
        get(3) -> IndexError

        Big-O: (write it here)
        """
        return ""

    def insert_at(self, index, song):
        """
        Put the song at this index, and move every song from that index
        onward one slot to the right, starting from the last song so nothing
        is overwritten. Index 0 is the front; an index equal to the count adds
        at the end. Grow first if every slot is full, as add does. Any other
        index raises an IndexError.

        With Midnight Bus, Slow Rain: insert_at(1, "Lemon Skies")
        -> Midnight Bus, Lemon Skies, Slow Rain

        Big-O: (write it here)
        """
        pass  # your code here

    def remove_at(self, index):
        """
        Take out the song at this index and return it. Move every song after
        it one slot to the left, to close the gap. The capacity stays the same.
        An index with no song raises an IndexError.

        With Midnight Bus, Lemon Skies, Slow Rain: remove_at(0) -> "Midnight Bus",
        and the playlist is Lemon Skies, Slow Rain

        Big-O: (write it here)
        """
        return ""
