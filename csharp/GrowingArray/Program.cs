using GrowingArray;

// Tries your playlist. Run it with: dotnet run --project GrowingArray
Playlist playlist = new Playlist();
foreach (string song in new string[] { "Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats", "Neon Garden" })
{
    playlist.Add(song);
}
Console.WriteLine("Added: " + Songs(playlist));
Console.WriteLine("Count: " + playlist.Count + ", capacity: " + playlist.Capacity);
playlist.InsertAt(0, "Golden Hour");
Console.WriteLine("Inserted Golden Hour at 0: " + Songs(playlist));
Console.WriteLine("Removed index 2: " + playlist.RemoveAt(2));
Console.WriteLine("Now: " + Songs(playlist));
Console.WriteLine("Index 3: " + playlist.Get(3));

static string Songs(Playlist playlist)
{
    List<string> songs = new List<string>();
    for (int i = 0; i < playlist.Count; i++)
    {
        songs.Add(playlist.Get(i));
    }
    return string.Join(", ", songs);
}
