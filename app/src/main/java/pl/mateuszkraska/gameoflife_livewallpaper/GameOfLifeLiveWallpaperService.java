package pl.mateuszkraska.gameoflife_livewallpaper;

import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;

import pl.mateuszkraska.gameoflife_livewallpaper.game.Simulation;
import pl.mateuszkraska.gameoflife_livewallpaper.render.GridPainter;

public class GameOfLifeLiveWallpaperService extends WallpaperService {
    @Override
    public Engine onCreateEngine() {
        return new WallpaperEngine();
    }

    public class WallpaperEngine extends WallpaperService.Engine implements SharedPreferences.OnSharedPreferenceChangeListener{

        private final long updateTime = 80;
        private final GridPainter gridPainter;
        private final Simulation simulation;
        private final Handler handler;
        private final Runnable runner;

        public WallpaperEngine() {
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).registerOnSharedPreferenceChangeListener(this);
            gridPainter = new GridPainter(
                    getSurfaceHolder(),
                    55,
                    PreferencesKeys.DEFAULT_BACKGROUND_COLOR,
                    PreferencesKeys.DEFAULT_FIELD_COLOR);
            refreshInformation(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

            simulation = new Simulation(61,115);

            handler = new Handler();
            runner = new Runnable() {
                @Override
                public void run() {
                    simulation.makeNextGeneration();
                    gridPainter.draw(simulation.getFieldStates());
                    handler.postDelayed(this,updateTime);
                }
            };
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            if(visible){
                handler.postDelayed(runner,updateTime);
            }else{
                handler.removeCallbacks(runner);
            }
            super.onVisibilityChanged(visible);
        }

        @Override
        public void onDestroy() {
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).unregisterOnSharedPreferenceChangeListener(this);
            super.onDestroy();
        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_UP){
                simulation.unlockAll();
            }else{
                float x = event.getX();
                float y = event.getY();
                simulation.makeFieldAliveAndLock(gridPainter.calculateFromInput(x,y));
            }
            super.onTouchEvent(event);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            refreshInformation(sharedPreferences);
        }

        private void refreshInformation(SharedPreferences sharedPreferences){
            gridPainter.setBackgroundColor(sharedPreferences.getInt(PreferencesKeys.BACKGROUND_COLOR_KEY , PreferencesKeys.DEFAULT_BACKGROUND_COLOR));
            gridPainter.setFieldColor(sharedPreferences.getInt(PreferencesKeys.FIELD_COLOR_KEY , PreferencesKeys.DEFAULT_FIELD_COLOR));
        }

    }
}