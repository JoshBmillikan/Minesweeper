package minesweeper;

import java.io.*;
import java.util.Random;

public class GameData implements Serializable {
    private final GameSettings settings;
    private Tile[][] tiles;


    public GameData(GameSettings settings, final long seed) {
        this.settings = settings;
    }

    public void Generate(final long seed) {
        var rand = new Random(seed);
        tiles = new Tile[settings.sizeX][settings.sizeY];
        for(var column : tiles) {
            for(int i=0;i<column.length;i++)
                column[i] = new Tile(rand.nextBoolean());
        }
        for(var column : tiles) {
            for(var tile : column)
                tile.Scan();
        }
    }
    public void Generate() {
        Generate(System.currentTimeMillis());
    }

    public void Save(final String filepath) throws IOException {
        var stream = new ObjectOutputStream(new FileOutputStream(filepath));
        stream.writeObject(this);
        stream.flush();
        stream.close();
    }

    public static GameData Load(final String filepath) throws IOException, ClassNotFoundException {
        var stream = new ObjectInputStream(new FileInputStream(filepath));
        return (GameData) stream.readObject();
    }
}
