# Exercise: Build a Growing Array

Exercise code for the lesson **Exercise: Build a Growing Array** in the course *Data Structures and Big-O, Demystified* on [ask-codey.com](https://www.ask-codey.com).

A music app's playlist keeps getting longer, so you'll build it the way every language builds its list: a plain array that moves to a bigger one, twice the size, when it fills up. The `Playlist` class already has its slots (an array of 2 to start), its count, and `count` and `capacity` to read them. There are four methods to finish:

- `add` (`Add` in C#): put a song at the end, growing first if every slot is full
- `get` (`Get`): the song at an index, or an error for an index with no song
- `insertAt` (`InsertAt`, `insert_at` in Python): put a song at an index, moving the songs after it one slot right
- `removeAt` (`RemoveAt`, `remove_at`): take out the song at an index and return it, moving the songs after it one slot left

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

## Tips

- **Start with `add` and `get`.** Most tests use them to set up and read the playlist, so they unlock the rest.
- **Moving day is its own small job.** A private helper method that makes the bigger array and copies the songs across keeps `add` and `insertAt` short, since both need it.
- **Move songs from the far end first.** When inserting, start with the last song and work back to the index, or each song overwrites the next before it has moved.
- **A spare slot isn't a song.** Check an index against the count, not the capacity.

## Hand it in

When it works, push your solution to a public repo of your own on GitHub, then paste that repo's address into **Submit your exercise** on the lesson page at [ask-codey.com](https://www.ask-codey.com). That's what completes the lesson. The lesson page shows the Git commands, step by step.
