package mobile.keithapps.drinkinggames;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Keith on 11/18/2015.
 */
public class SettingsMain extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        if (Build.VERSION.SDK_INT >= 21)
            this.setTheme(R.style.Theme_FullscreenTheme_MaterialDark);
    }

    public void save(View view) {

    }
}