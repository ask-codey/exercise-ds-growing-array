// A playlist built on a plain array that grows: your four methods are
// add, get, insertAt and removeAt. The comment above each one says what
// it should do, with examples. Replace each method's body with your own
// code, and write its Big-O in its comment. Keep the songs in #slots, an
// array of fixed size used as a plain array: don't call push, splice or
// pop on it, which would let TypeScript do the growing for you.
export class Playlist {
  #slots: string[] = new Array<string>(2);
  #count = 0;

  /** How many songs the playlist holds. */
  get count(): number {
    return this.#count;
  }

  /** How many slots the array has: the most songs it holds before it grows. */
  get capacity(): number {
    return this.#slots.length;
  }

  /*
   * Put the song in the next free slot, after the last song. If every
   * slot is full (count equals capacity), first make a new array twice the
   * size, copy every song across in order, and use the new array from then on.
   *
   * On a new playlist: add("Midnight Bus"), add("Lemon Skies") -> capacity 2
   * then add("Slow Rain") -> capacity 4, songs: Midnight Bus, Lemon Skies, Slow Rain
   *
   * Big-O: (write it here)
   */
  add(song: string): void {
    // Your code here.
  }

  /*
   * Return the song at this index, counting from 0. An index below 0, or
   * at or past the count, has no song: throw a RangeError.
   * A spare slot isn't a song, even though the slot exists.
   *
   * With Midnight Bus, Lemon Skies, Slow Rain: get(1) -> "Lemon Skies"
   * get(3) -> RangeError
   *
   * Big-O: (write it here)
   */
  get(index: number): string {
    return "";
  }

  /*
   * Put the song at this index, and move every song from that index
   * onward one slot to the right, starting from the last song so nothing
   * is overwritten. Index 0 is the front; an index equal to the count adds
   * at the end. Grow first if every slot is full, as add does. Any other
   * index throws a RangeError.
   *
   * With Midnight Bus, Slow Rain: insertAt(1, "Lemon Skies")
   * -> Midnight Bus, Lemon Skies, Slow Rain
   *
   * Big-O: (write it here)
   */
  insertAt(index: number, song: string): void {
    // Your code here.
  }

  /*
   * Take out the song at this index and return it. Move every song after
   * it one slot to the left, to close the gap. The capacity stays the same.
   * An index with no song throws a RangeError.
   *
   * With Midnight Bus, Lemon Skies, Slow Rain: removeAt(0) -> "Midnight Bus",
   * and the playlist is Lemon Skies, Slow Rain
   *
   * Big-O: (write it here)
   */
  removeAt(index: number): string {
    return "";
  }
}
