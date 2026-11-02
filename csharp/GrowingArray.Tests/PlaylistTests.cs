using System.Diagnostics;
using GrowingArray;

namespace GrowingArray.Tests;

// The tests for your playlist. You don't need to change this file.
// Each message says what was expected, so a failing test tells you what to look at.

[TestClass]
public class PlaylistTests
{
    private static Playlist Make(params string[] songs)
    {
        Playlist playlist = new Playlist();
        foreach (string song in songs)
        {
            playlist.Add(song);
        }
        return playlist;
    }

    private static List<string> Songs(Playlist playlist)
    {
        List<string> songs = new List<string>();
        for (int i = 0; i < playlist.Count; i++)
        {
            songs.Add(playlist.Get(i));
        }
        return songs;
    }

    [TestMethod]
    public void AddKeepsTheSongsInOrder()
    {
        Playlist playlist = Make("Midnight Bus", "Lemon Skies", "Slow Rain");
        Assert.AreEqual(3, playlist.Count, "Not yet: three songs were added, so the count is 3.");
        Assert.AreEqual("Midnight Bus", playlist.Get(0), "Not yet: the first song added is at index 0.");
        Assert.AreEqual("Slow Rain", playlist.Get(2), "Not yet: the third song added is at index 2.");
    }

    [TestMethod]
    public void CapacityStartsAtTwoAndDoublesWhenFull()
    {
        Playlist playlist = new Playlist();
        Assert.AreEqual(2, playlist.Capacity, "Not yet: a new playlist has 2 slots.");
        playlist.Add("Midnight Bus");
        playlist.Add("Lemon Skies");
        Assert.AreEqual(2, playlist.Capacity, "Not yet: 2 songs fit in 2 slots, so there's no moving day yet.");
        playlist.Add("Slow Rain");
        Assert.AreEqual(4, playlist.Capacity, "Not yet: the third song finds the array full, so the capacity doubles to 4.");
        playlist.Add("Paper Boats");
        playlist.Add("Neon Garden");
        Assert.AreEqual(8, playlist.Capacity, "Not yet: the fifth song finds 4 slots full, so the capacity doubles to 8.");
        CollectionAssert.AreEqual(new List<string> { "Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats", "Neon Garden" }, Songs(playlist),
            "Not yet: moving day copies every song across, in order.");
    }

    [TestMethod]
    public void GetRefusesAnIndexWithNoSong()
    {
        Playlist playlist = Make("Midnight Bus", "Lemon Skies", "Slow Rain");
        Assert.ThrowsExactly<ArgumentOutOfRangeException>(() => playlist.Get(-1), "Not yet: index -1 has no song.");
        Assert.ThrowsExactly<ArgumentOutOfRangeException>(() => playlist.Get(3), "Not yet: index 3 is a spare slot, not a song.");
    }

    [TestMethod]
    public void InsertAtTheFrontMovesEveryoneOver()
    {
        Playlist playlist = Make("Midnight Bus", "Lemon Skies", "Slow Rain");
        playlist.InsertAt(0, "Golden Hour");
        CollectionAssert.AreEqual(new List<string> { "Golden Hour", "Midnight Bus", "Lemon Skies", "Slow Rain" }, Songs(playlist),
            "Not yet: Golden Hour goes first, and every other song moves one slot right.");
    }

    [TestMethod]
    public void InsertInTheMiddleAndAtTheEnd()
    {
        Playlist playlist = Make("Midnight Bus", "Slow Rain");
        playlist.InsertAt(1, "Lemon Skies");
        CollectionAssert.AreEqual(new List<string> { "Midnight Bus", "Lemon Skies", "Slow Rain" }, Songs(playlist),
            "Not yet: Lemon Skies goes between Midnight Bus and Slow Rain.");
        playlist.InsertAt(3, "Paper Boats");
        CollectionAssert.AreEqual(new List<string> { "Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats" }, Songs(playlist),
            "Not yet: inserting at the count adds at the end.");
        Assert.AreEqual(4, playlist.Capacity, "Not yet: inserting into a full playlist grows it, as Add does.");
    }

    [TestMethod]
    public void InsertRefusesAnIndexPastTheEnd()
    {
        Playlist playlist = Make("Midnight Bus", "Lemon Skies");
        Assert.ThrowsExactly<ArgumentOutOfRangeException>(() => playlist.InsertAt(3, "Slow Rain"), "Not yet: with 2 songs, index 3 would leave a gap.");
        Assert.ThrowsExactly<ArgumentOutOfRangeException>(() => playlist.InsertAt(-1, "Slow Rain"), "Not yet: index -1 isn't a place in the playlist.");
    }

    [TestMethod]
    public void RemoveAtReturnsTheSongAndClosesTheGap()
    {
        Playlist playlist = Make("Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats");
        Assert.AreEqual("Lemon Skies", playlist.RemoveAt(1), "Not yet: RemoveAt hands back the song it took out.");
        CollectionAssert.AreEqual(new List<string> { "Midnight Bus", "Slow Rain", "Paper Boats" }, Songs(playlist),
            "Not yet: the songs after Lemon Skies move one slot left.");
        Assert.AreEqual("Midnight Bus", playlist.RemoveAt(0), "Not yet: index 0 is the first song.");
        Assert.AreEqual("Paper Boats", playlist.RemoveAt(1), "Not yet: index 1 is now the last song.");
        CollectionAssert.AreEqual(new List<string> { "Slow Rain" }, Songs(playlist), "Not yet: one song is left.");
        Assert.AreEqual(4, playlist.Capacity, "Not yet: removing doesn't change the capacity.");
    }

    [TestMethod]
    public void RemoveAtRefusesAnIndexWithNoSong()
    {
        Playlist playlist = Make("Midnight Bus");
        Assert.ThrowsExactly<ArgumentOutOfRangeException>(() => playlist.RemoveAt(1), "Not yet: with 1 song, index 1 has no song.");
        playlist.RemoveAt(0);
        Assert.ThrowsExactly<ArgumentOutOfRangeException>(() => playlist.RemoveAt(0), "Not yet: an empty playlist has nothing to remove.");
    }

    [TestMethod]
    public void AMillionSongsStayFast()
    {
        Playlist playlist = new Playlist();
        Stopwatch watch = Stopwatch.StartNew();
        for (int i = 0; i < 1000000; i++)
        {
            playlist.Add("Song " + i);
            if (i % 10000 == 0 && watch.Elapsed.TotalSeconds > 2)
            {
                break;
            }
        }
        watch.Stop();
        Assert.IsTrue(watch.Elapsed.TotalSeconds < 2, "Not yet: adding 1,000,000 songs ran past 2 seconds: it was stopped at " + watch.Elapsed.TotalSeconds.ToString("0.0") + " seconds, with " + playlist.Count + " songs added. When moving day is rare, a million adds take well under one second.");
        Assert.AreEqual(1000000, playlist.Count, "Not yet: 1,000,000 songs were added.");
        Assert.AreEqual("Song 0", playlist.Get(0), "Not yet: the first song is at index 0.");
        Assert.AreEqual("Song 999999", playlist.Get(999999), "Not yet: the last song is at index 999,999.");
    }
}
