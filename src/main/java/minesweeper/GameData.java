package minesweeper;

import java.io.*;
import java.util.Random;

public class GameData implements Serializable {
    private final GameSettings settings;
    private Tile[][] tiles;


    public void Lose() {

    }

    public GameData(GameSettings settings, final long seed) {
        this.settings = settings;
    }

    public Tile getTile(final int x, final int y) {
        return tiles[x][y];
    }

    public void Generate(final long seed) {
        var rand = new Random(seed);
        tiles = new Tile[settings.sizeX][settings.sizeY];
        for(int i=0;i<tiles.length;i++) {
            for(int j=0;j<tiles[i].length;j++)
                tiles[i][j] = new Tile(rand.nextBoolean(),i,j);
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
