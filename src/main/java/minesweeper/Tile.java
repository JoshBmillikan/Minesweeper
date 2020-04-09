package minesweeper;

public class Tile {
    public final boolean isMine;
    private final int x,y;
    private int NumberOfAdjacentMines = 0;
    private boolean isRevealed = false;

    Tile(final boolean isMine, final int x, final int y) {
        this.isMine = isMine;
        this.x = x;
        this.y = y;
    }

    public void Reveal() {
        if(isMine)
            Main.game.Lose();
        isRevealed = true;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    void Scan() {
        var game = Main.game;
        if(game != null) {
            if(game.getTile(x+1,y).isMine)
                NumberOfAdjacentMines++;
            if(game.getTile(x-1,y).isMine)
                NumberOfAdjacentMines++;
            if(game.getTile(x,y+1).isMine)
                NumberOfAdjacentMines++;
            if(game.getTile(x,y-1).isMine)
                NumberOfAdjacentMines++;
        }
    }
}
