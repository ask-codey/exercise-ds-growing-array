// The tests for your playlist. You don't need to change this file.
// Each message says what was expected, so a failing test tells you what to look at.
import assert from "node:assert/strict";
import { test } from "node:test";
import { Playlist } from "./growing-array.ts";

function make(...songs: string[]): Playlist {
  const playlist = new Playlist();
  for (const song of songs) {
    playlist.add(song);
  }
  return playlist;
}

function songs(playlist: Playlist): string[] {
  const titles: string[] = [];
  for (let i = 0; i < playlist.count; i++) {
    titles.push(playlist.get(i));
  }
  return titles;
}

test("add keeps the songs in order", () => {
  const playlist = make("Midnight Bus", "Lemon Skies", "Slow Rain");
  assert.equal(playlist.count, 3, "Not yet: three songs were added, so the count is 3.");
  assert.equal(playlist.get(0), "Midnight Bus", "Not yet: the first song added is at index 0.");
  assert.equal(playlist.get(2), "Slow Rain", "Not yet: the third song added is at index 2.");
});

test("capacity starts at 2 and doubles when full", () => {
  const playlist = new Playlist();
  assert.equal(playlist.capacity, 2, "Not yet: a new playlist has 2 slots.");
  playlist.add("Midnight Bus");
  playlist.add("Lemon Skies");
  assert.equal(playlist.capacity, 2, "Not yet: 2 songs fit in 2 slots, so there's no moving day yet.");
  playlist.add("Slow Rain");
  assert.equal(playlist.capacity, 4, "Not yet: the third song finds the array full, so the capacity doubles to 4.");
  playlist.add("Paper Boats");
  playlist.add("Neon Garden");
  assert.equal(playlist.capacity, 8, "Not yet: the fifth song finds 4 slots full, so the capacity doubles to 8.");
  assert.deepEqual(songs(playlist), ["Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats", "Neon Garden"],
    "Not yet: moving day copies every song across, in order.");
});

test("get refuses an index with no song", () => {
  const playlist = make("Midnight Bus", "Lemon Skies", "Slow Rain");
  assert.throws(() => playlist.get(-1), RangeError, "Not yet: index -1 has no song.");
  assert.throws(() => playlist.get(3), RangeError, "Not yet: index 3 is a spare slot, not a song.");
});

test("insert at the front moves everyone over", () => {
  const playlist = make("Midnight Bus", "Lemon Skies", "Slow Rain");
  playlist.insertAt(0, "Golden Hour");
  assert.deepEqual(songs(playlist), ["Golden Hour", "Midnight Bus", "Lemon Skies", "Slow Rain"],
    "Not yet: Golden Hour goes first, and every other song moves one slot right.");
});

test("insert in the middle and at the end", () => {
  const playlist = make("Midnight Bus", "Slow Rain");
  playlist.insertAt(1, "Lemon Skies");
  assert.deepEqual(songs(playlist), ["Midnight Bus", "Lemon Skies", "Slow Rain"],
    "Not yet: Lemon Skies goes between Midnight Bus and Slow Rain.");
  playlist.insertAt(3, "Paper Boats");
  assert.deepEqual(songs(playlist), ["Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats"],
    "Not yet: inserting at the count adds at the end.");
  assert.equal(playlist.capacity, 4, "Not yet: inserting into a full playlist grows it, as add does.");
});

test("insert refuses an index past the end", () => {
  const playlist = make("Midnight Bus", "Lemon Skies");
  assert.throws(() => playlist.insertAt(3, "Slow Rain"), RangeError, "Not yet: with 2 songs, index 3 would leave a gap.");
  assert.throws(() => playlist.insertAt(-1, "Slow Rain"), RangeError, "Not yet: index -1 isn't a place in the playlist.");
});

test("removeAt returns the song and closes the gap", () => {
  const playlist = make("Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats");
  assert.equal(playlist.removeAt(1), "Lemon Skies", "Not yet: removeAt hands back the song it took out.");
  assert.deepEqual(songs(playlist), ["Midnight Bus", "Slow Rain", "Paper Boats"],
    "Not yet: the songs after Lemon Skies move one slot left.");
  assert.equal(playlist.removeAt(0), "Midnight Bus", "Not yet: index 0 is the first song.");
  assert.equal(playlist.removeAt(1), "Paper Boats", "Not yet: index 1 is now the last song.");
  assert.deepEqual(songs(playlist), ["Slow Rain"], "Not yet: one song is left.");
  assert.equal(playlist.capacity, 4, "Not yet: removing doesn't change the capacity.");
});

test("removeAt refuses an index with no song", () => {
  const playlist = make("Midnight Bus");
  assert.throws(() => playlist.removeAt(1), RangeError, "Not yet: with 1 song, index 1 has no song.");
  playlist.removeAt(0);
  assert.throws(() => playlist.removeAt(0), RangeError, "Not yet: an empty playlist has nothing to remove.");
});

test("a thousand songs", () => {
  const playlist = new Playlist();
  for (let i = 0; i < 1000; i++) {
    playlist.add(`Song ${i}`);
  }
  assert.equal(playlist.count, 1000, "Not yet: 1,000 songs were added.");
  assert.equal(playlist.capacity, 1024, "Not yet: doubling from 2 gives 1,024 slots for 1,000 songs.");
  assert.equal(playlist.get(999), "Song 999", "Not yet: the last song is at index 999.");
});
