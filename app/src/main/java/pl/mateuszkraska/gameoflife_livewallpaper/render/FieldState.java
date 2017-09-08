package pl.mateuszkraska.gameoflife_livewallpaper.render;

public class FieldState {

    private boolean isActive;

    public FieldState(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }
}
