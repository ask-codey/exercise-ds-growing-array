// Tries your playlist. Run it with: node demo.js
import { Playlist } from "./growing-array.js";

function songs(playlist) {
  const titles = [];
  for (let i = 0; i < playlist.count; i++) {
    titles.push(playlist.get(i));
  }
  return titles.join(", ");
}

const playlist = new Playlist();
for (const song of ["Midnight Bus", "Lemon Skies", "Slow Rain", "Paper Boats", "Neon Garden"]) {
  playlist.add(song);
}
console.log(`Added: ${songs(playlist)}`);
console.log(`Count: ${playlist.count}, capacity: ${playlist.capacity}`);
playlist.insertAt(0, "Golden Hour");
console.log(`Inserted Golden Hour at 0: ${songs(playlist)}`);
console.log(`Removed index 2: ${playlist.removeAt(2)}`);
console.log(`Now: ${songs(playlist)}`);
console.log(`Index 3: ${playlist.get(3)}`);
