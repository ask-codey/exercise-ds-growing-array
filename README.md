# Exercise: Build a Growing Array

Exercise code for the lesson **Exercise: Build a Growing Array** in the course *Data Structures and Big-O, Demystified* on [ask-codey.com](https://www.ask-codey.com).

## The ticket

> **From:** the product lead, the music app
>
> Our playlists live in a plain array of 8 slots, and the 9th song crashes the app. People have playlists of thousands of songs, so the new playlist has to grow.
>
> 1. **No limit.** A playlist takes as many songs as people add: 9, 10,000 or a million.
> 2. **Adding stays quick.** A million songs, added one after another, in under 2 seconds.
> 3. **Any song opens at once** by its number, however long the playlist is: Shuffle jumps straight to song 500,000.
> 4. **Songs go in and come out anywhere,** at the top, in the middle or at the end, and the others keep their order.
> 5. **Phones are short of memory.** Start with 2 slots. When every slot is full, the platform team's rule is to move to an array twice the size; removing songs never shrinks it.

The code that's there has the `Playlist` class, its 2 slots and its count, and `count` and `capacity` to read them. Four methods are yours to finish:

- `add` (`Add` in C#): put a song at the end, moving to a bigger array first if every slot is full
- `get` (`Get`): the song at an index, or an error for an index with no song
- `insertAt` (`InsertAt`, `insert_at` in Python): put a song at an index, moving the songs after it one slot right
- `removeAt` (`RemoveAt`, `remove_at`): take out the song at an index and return it, moving the songs after it one slot left

Nine tests check it, ending with a million songs added in under 2 seconds.

## Pick your language

The same exercise is here in five languages. Work in the folder for the language you set up; you only need one.

| Folder | Your code | The tests |
|---|---|---|
| `csharp/` | `GrowingArray/Playlist.cs` | `GrowingArray.Tests/PlaylistTests.cs` |
| `java/` | `src/main/java/Playlist.java` | `src/test/java/PlaylistTest.java` |
| `python/` | `growing_array.py` | `test_growing_array.py` |
| `javascript/` | `growing-array.js` | `growing-array.test.js` |
| `typescript/` | `growing-array.ts` | `growing-array.test.ts` |

Each method does nothing, or returns an empty string, for now. The comment above it says what it should do, with examples. Replace its body with your own code, and write its Big-O on its **Big-O:** line.

Keep the songs in the class's own array of slots. Your language's list (`List<string>`, `ArrayList`, `append`, `push`) would do the growing for you, and growing it yourself is the point.

Every folder also has a small demo program that tries your playlist with five songs. You don't need to change the tests or the demo.

## Run it and check your work

Open your language's folder in VS Code (**File > Open Folder**), then open its terminal (**Terminal > New Terminal**) and run these commands there.

**C#**

```bash
dotnet run --project GrowingArray
dotnet test
```

**Java** (the Maven Wrapper downloads Maven the first time, so the first run takes a minute)

```bash
java src/main/java/Demo.java
./mvnw test
```

On Windows in PowerShell, write `.\mvnw test` instead of `./mvnw test`.

**Python** (once: make a virtual environment and install pytest into it)

```bash
python -m venv .venv
```

Then turn it on: `.venv\Scripts\Activate.ps1` in PowerShell on Windows, or `source .venv/bin/activate` on macOS (where the command is `python3`, not `python`). Then:

```bash
pip install -r requirements.txt
python demo.py
python -m pytest
```

**JavaScript**

```bash
node demo.js
npm test
```

**TypeScript** (once: `npm install`, which installs TypeScript)

```bash
node demo.ts
npm test
```

At first every test fails: that is expected. Each method you finish turns its tests green. A failing test's message starts with **Not yet:** and says what it expected.

<details>
<summary>Stuck?</summary>

- **Where to look:** the ticket asks for what the lesson *The Ninth Song Won't Fit: Growing Arrays* builds, a **growing array** (also called a dynamic array): a plain array with spare slots, a count of the slots in use, and a moving day when it's full.
- **Start with `add` and `get`.** Most tests use them to set up and read the playlist, so they unlock the rest.
- **Moving day is its own small job.** A private helper method that makes the bigger array and copies the songs across keeps `add` and `insertAt` short, since both need it.
- **Move songs from the far end first.** When inserting, start with the last song and work back to the index, or each song overwrites the next before it has moved.
- **A spare slot isn't a song.** Check an index against the count, not the capacity. For inserting, the count itself is allowed (that's the end); one past it isn't.
- **The million-song test stops early** once it passes 2 seconds, and says how many songs it got through. If yours stops there, count your moving days: *Is Adding a Song Slow? Amortized Cost* shows why their size matters.

</details>

<details>
<summary>Why Should I Choose This?</summary>

Open this once your tests are green. The test adds a million songs, one after another.

- **One fixed array, made huge (10,000 or a million slots):** every add is instant until the cap, then the app crashes again, and a 3-song playlist still holds a million slots of memory.
- **Grow by one slot each time:** every add is a moving day that copies every song so far, about 500 billion copies for a million songs. O(n²): it never finishes.
- **Grow by 100 slots each time:** a moving day every 100 songs, still O(n²), about 5 billion copies. On the test's clock it was stopped at 2 seconds after 110,000 songs in Python and 520,000 in Java.
- **Double:** moving days at 2, 4, 8 and so on up to 524,288 songs, 19 of them, and 1,048,574 copies in all. That's about one copy per song, so each add is O(1) on average, and the million songs take a fraction of a second (0.3 seconds in Python).
- **What it gives up:** right after a move, up to half the slots stand empty, and the one add that lands on moving day copies everything. Adding at the top still moves every song: that's the next chapter's problem.

</details>

## Hand it in

When it works, push your solution to a public repo of your own on GitHub, then paste that repo's address into **Submit your exercise** on the lesson page at [ask-codey.com](https://www.ask-codey.com). That's what completes the lesson. The lesson page shows the Git commands, step by step.
