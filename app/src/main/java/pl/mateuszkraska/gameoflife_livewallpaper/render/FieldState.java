package pl.mateuszkraska.gameoflife_livewallpaper.render;

public class FieldState {

    private final boolean isActive;

    public FieldState(boolean isActive) {
        this.isActive = isActive;
    }

    boolean isActive() {
        return isActive;
    }
}