package mobile.keithapps.drinkinggames;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

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

        SharedPreferences prefs = this.getSharedPreferences(getString(R.string.text_package), Context.MODE_PRIVATE);

        boolean acesLow = prefs.getBoolean(getString(R.string.setting_acesalwayslow), true);
        ((CheckBox) this.findViewById(R.id.settingsscreen_acesarelow)).setChecked(acesLow);

        //Put Current Values in
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_ace_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_ace_actionname_key),
                        getString(R.string.circleofdeath_carddirection_ace)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_ace_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_ace_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_ace)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_two_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_two_actionname_key),
                        getString(R.string.circleofdeath_carddirection_two)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_two_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_two_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_two)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_three_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_three_actionname_key),
                        getString(R.string.circleofdeath_carddirection_three)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_three_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_three_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_three)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_four_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_four_actionname_key),
                        getString(R.string.circleofdeath_carddirection_four)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_four_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_four_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_four)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_five_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_five_actionname_key),
                        getString(R.string.circleofdeath_carddirection_five)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_five_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_five_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_five)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_six_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_six_actionname_key),
                        getString(R.string.circleofdeath_carddirection_six)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_six_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_six_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_six)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_seven_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_seven_actionname_key),
                        getString(R.string.circleofdeath_carddirection_seven)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_seven_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_seven_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_seven)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_eight_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_eight_actionname_key),
                        getString(R.string.circleofdeath_carddirection_eight)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_eight_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_eight_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_eight)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_nine_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_nine_actionname_key),
                        getString(R.string.circleofdeath_carddirection_nine)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_nine_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_nine_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_nine)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_ten_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_ten_actionname_key),
                        getString(R.string.circleofdeath_carddirection_ten)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_ten_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_ten_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_ten)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_jack_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_jack_actionname_key),
                        getString(R.string.circleofdeath_carddirection_jack)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_jack_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_jack_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_jack)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_queen_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_queen_actionname_key),
                        getString(R.string.circleofdeath_carddirection_queen)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_queen_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_queen_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_queen)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_king_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_king_actionname_key),
                        getString(R.string.circleofdeath_carddirection_king)));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_king_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_king_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_king)));
    }

    private void resetToDefault() {
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_ace_actionname))
                .setText(getString(R.string.circleofdeath_carddirection_ace));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_ace_actiontext))
                .setText(getString(R.string.circleofdeath_carddirection_description_ace));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_two_actionname))
                .setText(getString(R.string.circleofdeath_carddirection_two));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_two_actiontext))
                .setText(getString(R.string.circleofdeath_carddirection_description_two));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_three_actionname))
                .setText(getString(R.string.circleofdeath_carddirection_three));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_three_actiontext))
                .setText(getString(R.string.circleofdeath_carddirection_description_three));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_four_actionname))
                .setText(getString(R.string.circleofdeath_carddirection_four));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_four_actiontext))
                .setText(getString(R.string.circleofdeath_carddirection_description_four));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_five_actionname))
                .setText(getString(R.string.circleofdeath_carddirection_five));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_five_actiontext))
                .setText(getString(R.string.circleofdeath_carddirection_description_five));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_six_actionname))
                .setText(getString(R.string.circleofdeath_carddirection_six));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_six_actiontext))
                .setText(getString(R.string.circleofdeath_carddirection_description_six));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_seven_actionname))
                .setText(getString(R.string.circleofdeath_carddirection_seven));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_seven_actiontext))
                .setText(getString(R.string.circleofdeath_carddirection_description_seven));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_eight_actionname))
                .setText(getString(R.string.circleofdeath_carddirection_eight));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_eight_actiontext))
                .setText(getString(R.string.circleofdeath_carddirection_description_eight));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_nine_actionname))
                .setText(getString(R.string.circleofdeath_carddirection_nine));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_nine_actiontext))
                .setText(getString(R.string.circleofdeath_carddirection_description_nine));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_ten_actionname))
                .setText(getString(R.string.circleofdeath_carddirection_ten));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_ten_actiontext))
                .setText(getString(R.string.circleofdeath_carddirection_description_ten));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_jack_actionname))
                .setText(getString(R.string.circleofdeath_carddirection_jack));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_jack_actiontext))
                .setText(getString(R.string.circleofdeath_carddirection_description_jack));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_queen_actionname))
                .setText(getString(R.string.circleofdeath_carddirection_queen));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_queen_actiontext))
                .setText(getString(R.string.circleofdeath_carddirection_description_queen));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_king_actionname))
                .setText(getString(R.string.circleofdeath_carddirection_king));
        ((EditText) findViewById(R.id.settingsscreen_circleofdeath_king_actiontext))
                .setText(getString(R.string.circleofdeath_carddirection_description_king));
    }

    public void save(View view) {
        SharedPreferences.Editor prefsEditor = this.getSharedPreferences(
                getString(R.string.text_package), Context.MODE_PRIVATE).edit();
        prefsEditor.putBoolean(getString(R.string.setting_acesalwayslow),
                ((CheckBox) findViewById(R.id.settingsscreen_acesarelow)).isChecked());

        prefsEditor.putString(getString(R.string.settings_cod_ace_actionname_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_ace_actionname))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_ace_actiontext_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_ace_actiontext))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_two_actionname_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_two_actionname))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_two_actiontext_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_two_actiontext))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_three_actionname_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_three_actionname))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_three_actiontext_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_three_actiontext))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_four_actionname_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_four_actionname))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_four_actiontext_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_four_actiontext))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_five_actionname_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_five_actionname))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_five_actiontext_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_five_actiontext))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_six_actionname_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_six_actionname))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_six_actiontext_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_six_actiontext))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_seven_actionname_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_seven_actionname))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_seven_actiontext_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_seven_actiontext))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_eight_actionname_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_eight_actionname))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_eight_actiontext_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_eight_actiontext))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_nine_actionname_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_nine_actionname))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_nine_actiontext_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_nine_actiontext))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_ten_actionname_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_ten_actionname))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_ten_actiontext_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_ten_actiontext))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_jack_actionname_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_jack_actionname))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_jack_actiontext_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_jack_actiontext))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_queen_actionname_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_queen_actionname))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_queen_actiontext_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_queen_actiontext))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_king_actionname_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_king_actionname))
                        .getText().toString());
        prefsEditor.putString(getString(R.string.settings_cod_king_actiontext_key),
                ((EditText) findViewById(R.id.settingsscreen_circleofdeath_king_actiontext))
                        .getText().toString());
        prefsEditor.apply();
        Intent i = new Intent(getApplicationContext(), GamesMenuMain.class);
        startActivity(i);
    }
}