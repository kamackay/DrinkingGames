package mobile.keithapps.drinkinggames;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

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

        SharedPreferences prefs = this.getSharedPreferences("mobile.keithapps.drinkinggames", Context.MODE_PRIVATE);

        boolean acesLow = prefs.getBoolean(getString(R.string.setting_acesalwayslow), true);
        ((CheckBox) this.findViewById(R.id.settingsscreen_acesarelow)).setChecked(acesLow);
    }

    public void save(View view) {
        SharedPreferences.Editor prefsEditor = this.getSharedPreferences(
                getString(R.string.text_package), Context.MODE_PRIVATE).edit();
        prefsEditor.putBoolean(getString(R.string.setting_acesalwayslow),
                ((CheckBox) findViewById(R.id.settingsscreen_acesarelow)).isChecked());
        prefsEditor.apply();
        Intent i = new Intent(getApplicationContext(), GamesMenuMain.class);
        startActivity(i);
    }
}