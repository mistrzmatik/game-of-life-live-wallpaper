package pl.mateuszkraska.gameoflife_livewallpaper;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SettingsActivity extends Activity {

    private ImageView backgroundColorButton, fieldColorButton;

    private LinearLayout colorPickerLayout;
    private int actualColorSelectTo = 0;
    private final int colorSelectBackground = 1;
    private final int colorSelectField = 2;
    private SharedPreferences preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);

        preferenceManager = PreferenceManager.getDefaultSharedPreferences(this);

        backgroundColorButton = (ImageView)findViewById(R.id.backround_color_button);
        backgroundColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualColorSelectTo = colorSelectBackground;
                openColorPicker();
            }
        });

        fieldColorButton = (ImageView)findViewById(R.id.field_color_button);
        fieldColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualColorSelectTo = colorSelectField;
                openColorPicker();
            }
        });

        colorPickerLayout = (LinearLayout)findViewById(R.id.color_select);
        for( int i = 0 ; i < colorPickerLayout.getChildCount() ; i++ ){
            LinearLayout linearLayout = (LinearLayout)colorPickerLayout.getChildAt(i);
            for( int j = 0 ; j < linearLayout.getChildCount() ; j++ ){
                final View colorView = linearLayout.getChildAt(j);
                colorView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int color = 0;
                        Drawable background = colorView.getBackground();
                        if (background instanceof ColorDrawable) {
                            color = ((ColorDrawable)background).getColor();
                        }
                        selectColor(color);
                    }
                });
            }
        }

        findViewById(R.id.apply_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                finish();
            }
        });

        loadColorFromPreferences();

    }

    private void loadColorFromPreferences(){
        backgroundColorButton.setBackgroundColor(preferenceManager.getInt(PreferencesKeys.BACKGROUND_COLOR_KEY,PreferencesKeys.DEFAULT_BACKGROUND_COLOR));
        fieldColorButton.setBackgroundColor(preferenceManager.getInt(PreferencesKeys.FIELD_COLOR_KEY,PreferencesKeys.DEFAULT_FIELD_COLOR));
    }

    private void selectColor(int color){
        if(actualColorSelectTo == colorSelectBackground){
            backgroundColorButton.setBackgroundColor(color);
        }else if(actualColorSelectTo == colorSelectField){
            fieldColorButton.setBackgroundColor(color);
        }
        closeColorPicker();
    }

    private void openColorPicker(){
        colorPickerLayout.setVisibility(View.VISIBLE);
    }

    private void closeColorPicker(){
        colorPickerLayout.setVisibility(View.GONE);
    }

    private void save(){
        preferenceManager.edit().putInt(PreferencesKeys.BACKGROUND_COLOR_KEY,((ColorDrawable)backgroundColorButton.getBackground()).getColor()).apply();
        preferenceManager.edit().putInt(PreferencesKeys.FIELD_COLOR_KEY,((ColorDrawable)fieldColorButton.getBackground()).getColor()).apply();
    }

}