package pl.mateuszkraska.gameoflife_livewallpaper.game;

public class Coordinates {

    private final int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}