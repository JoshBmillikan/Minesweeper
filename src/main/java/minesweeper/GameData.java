package minesweeper;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;

public class GameData implements Serializable {
    private final GameSettings settings;
    private final String GameSeed;
    private Tile[][] tiles;


    public void Lose() {

    }

    public GameSettings.Difficulty getDifficulty() {
        return settings.difficulty;
    }

    public GameData(GameSettings settings, final String seed) {
        this.settings = settings;
        GameSeed = seed;
    }

    public Tile getTile(final int x, final int y) {
        if(x < 0 || y < 0)
            return null;
        if(x >= tiles.length || y >= tiles[0].length)
            return null;
        return tiles[x][y];
    }

    public GameSettings getSettings() {
        return settings;
    }

    private void Generate(final long seed) {
        var rand = new Random(seed);
        tiles = new Tile[settings.sizeX][settings.sizeY];
        for(int i=0;i<tiles.length;i++) {
            for(int j=0;j<tiles[i].length;j++)
                tiles[i][j] = new Tile(rand.nextInt(101),i,j);
        }
        for(var column : tiles) {
            for(var tile : column)
                tile.Scan();
        }
    }
    public void Generate() {
        if(GameSeed == null)
            Generate(System.currentTimeMillis());
        else {
            var bytes = Arrays.copyOf(GameSeed.getBytes(),8);
            Generate(ByteBuffer.wrap(bytes).getLong());
        }
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
