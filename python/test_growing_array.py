# The tests for your playlist. You don't need to change this file.
# Each message says what was expected, so a failing test tells you what to look at.
import time

import pytest

from growing_array import Playlist


def make(*songs):
    playlist = Playlist()
    for song in songs:
        playlist.add(song)
    return playlist


def songs(playlist):
    return [playlist.get(i) for i in range(playlist.count)]


def test_add_keeps_the_songs_in_order():
    playlist = make("Midnight Bus", "Lemon Skies", "Slow Rain")
    assert playlist.count == 3, "Not yet: three songs were added, so the count is 3."
    assert playlist.get(0) == "Midnight Bus", "Not yet: the first song added is at index 0."
    assert playlist.get(2) == "Slow Rain", "Not yet: the third song added is at index 2."


def test_capacity_starts_at_two_and_doubles_when_full():
    playlist = Playlist()
    assert playlist.capacity == 2, "Not yet: a new playlist has 2 slots."
    playlist.add("Midnight Bus")
    playlist.add("Lemon Skies")
    assert playlist.capacity == 2, "Not yet: 2 songs fit in 2 slots, so there's no moving day yet."
    playlist.add("Slow Rain")
    assert playlist.capacity == 4, "Not yet: the third song finds the array full, so the capacity doubles to 4."
    playlist.add("Paper Boats")
    playlist.add("Neon Garden")
    assert playlist.capacity == 8, "Not yet: the fifth song finds 4 slots full, so the capacity doubles to 8."
    assert songs(playlist) == ["Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats", "Neon Garden"], \
        "Not yet: moving day copies every song across, in order."


def test_get_refuses_an_index_with_no_song():
    playlist = make("Midnight Bus", "Lemon Skies", "Slow Rain")
    with pytest.raises(IndexError):
        playlist.get(-1)  # Not yet: index -1 has no song.
    with pytest.raises(IndexError):
        playlist.get(3)  # Not yet: index 3 is a spare slot, not a song.


def test_insert_at_the_front_moves_everyone_over():
    playlist = make("Midnight Bus", "Lemon Skies", "Slow Rain")
    playlist.insert_at(0, "Golden Hour")
    assert songs(playlist) == ["Golden Hour", "Midnight Bus", "Lemon Skies", "Slow Rain"], \
        "Not yet: Golden Hour goes first, and every other song moves one slot right."


def test_insert_in_the_middle_and_at_the_end():
    playlist = make("Midnight Bus", "Slow Rain")
    playlist.insert_at(1, "Lemon Skies")
    assert songs(playlist) == ["Midnight Bus", "Lemon Skies", "Slow Rain"], \
        "Not yet: Lemon Skies goes between Midnight Bus and Slow Rain."
    playlist.insert_at(3, "Paper Boats")
    assert songs(playlist) == ["Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats"], \
        "Not yet: inserting at the count adds at the end."
    assert playlist.capacity == 4, "Not yet: inserting into a full playlist grows it, as add does."


def test_insert_refuses_an_index_past_the_end():
    playlist = make("Midnight Bus", "Lemon Skies")
    with pytest.raises(IndexError):
        playlist.insert_at(3, "Slow Rain")  # Not yet: with 2 songs, index 3 would leave a gap.
    with pytest.raises(IndexError):
        playlist.insert_at(-1, "Slow Rain")  # Not yet: index -1 isn't a place in the playlist.


def test_remove_at_returns_the_song_and_closes_the_gap():
    playlist = make("Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats")
    assert playlist.remove_at(1) == "Lemon Skies", "Not yet: remove_at hands back the song it took out."
    assert songs(playlist) == ["Midnight Bus", "Slow Rain", "Paper Boats"], \
        "Not yet: the songs after Lemon Skies move one slot left."
    assert playlist.remove_at(0) == "Midnight Bus", "Not yet: index 0 is the first song."
    assert playlist.remove_at(1) == "Paper Boats", "Not yet: index 1 is now the last song."
    assert songs(playlist) == ["Slow Rain"], "Not yet: one song is left."
    assert playlist.capacity == 4, "Not yet: removing doesn't change the capacity."


def test_remove_at_refuses_an_index_with_no_song():
    playlist = make("Midnight Bus")
    with pytest.raises(IndexError):
        playlist.remove_at(1)  # Not yet: with 1 song, index 1 has no song.
    playlist.remove_at(0)
    with pytest.raises(IndexError):
        playlist.remove_at(0)  # Not yet: an empty playlist has nothing to remove.


def test_a_million_songs_stay_fast():
    playlist = Playlist()
    started = time.perf_counter()
    for i in range(1000000):
        playlist.add(f"Song {i}")
        if i % 10000 == 0 and time.perf_counter() - started > 2:
            break
    seconds = time.perf_counter() - started
    assert seconds < 2, f"Not yet: adding 1,000,000 songs ran past 2 seconds: it was stopped at {seconds:.1f} seconds, with {playlist.count} songs added. When moving day is rare, a million adds take well under one second."
    assert playlist.count == 1000000, "Not yet: 1,000,000 songs were added."
    assert playlist.get(0) == "Song 0", "Not yet: the first song is at index 0."
    assert playlist.get(999999) == "Song 999999", "Not yet: the last song is at index 999,999."
