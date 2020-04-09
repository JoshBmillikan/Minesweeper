package minesweeper;

import java.io.IOException;
import java.io.UncheckedIOException;

public class GameSettings {
    public enum Difficulty {
        EASY(80),
        NORMAL(66),
        HARD(50),
        INSANE(20);

        public final int threshold;

        Difficulty(final int threshold) {
            this.threshold = threshold;
        }
    }
    int sizeX, sizeY;
    Difficulty difficulty;

    public GameSettings(final int sizeX, final int sizeY, final Difficulty difficulty) throws UncheckedIOException {
        if(difficulty == null)
            throw new UncheckedIOException(new IOException("Difficulty was null"));
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.difficulty = difficulty;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
