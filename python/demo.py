# Tries your playlist. Run it with: python demo.py
from growing_array import Playlist


def songs(playlist):
    return ", ".join(playlist.get(i) for i in range(playlist.count))


playlist = Playlist()
for song in ["Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats", "Neon Garden"]:
    playlist.add(song)
print("Added: " + songs(playlist))
print(f"Count: {playlist.count}, capacity: {playlist.capacity}")
playlist.insert_at(0, "Golden Hour")
print("Inserted Golden Hour at 0: " + songs(playlist))
print("Removed index 2: " + playlist.remove_at(2))
print("Now: " + songs(playlist))
print("Index 3: " + playlist.get(3))
