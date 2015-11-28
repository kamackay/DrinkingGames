package mobile.keithapps.drinkinggames;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Keith on 11/19/2015.
 */
public class DrinkingGamesGlobal {
    /**
     * Get a random insult from the strings in the list in the memory
     *
     * @param res the SharedResources
     * @return a random insult
     */
    public static String getRandomInsult(Resources res) {
        String[] insults = res.getStringArray(R.array.insults);
        return insults[new Random().nextInt(insults.length)];
    }

    /**
     * Get the drawable for a die
     * @param dieVal the value of the die needed
     * @return the drawable for the given die value
     */
    public static int getDrawableIdForDie(int dieVal) {
        switch (dieVal) {
            case 1:
                return R.drawable.die_1;
            case 2:
                return R.drawable.die_2;
            case 3:
                return R.drawable.die_3;
            case 4:
                return R.drawable.die_4;
            case 5:
                return R.drawable.die_5;
            case 6:
                return R.drawable.die_6;
        }
        return R.drawable.die_1;
    }

    /**
     * Log an exception
     * @param e the exception, duh
     */
    public static void logException(Exception e) {
        Log.e("keithapps.drinkinggames", e.getMessage(), e);
    }

    public static View loadSettingsToLayout(View layout, SharedPreferences prefs, Activity t) {
        ((CheckBox) layout.findViewById(R.id.settingsscreen_bizkit_insult))
                .setChecked(prefs.getBoolean(t.getString(R.string.settings_bizkit_insultbizkit), true));
        ((CheckBox) layout.findViewById(R.id.settingsscreen_acesarelow))
                .setChecked(prefs.getBoolean(t.getString(R.string.setting_acesalwayslow), true));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ace_actionname))
                .setText(prefs.getString(t.getString(R.string.settings_cod_ace_actionname_key),
                        t.getString(R.string.circleofdeath_carddirection_ace)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ace_actiontext))
                .setText(prefs.getString(t.getString(R.string.settings_cod_ace_actiontext_key),
                        t.getString(R.string.circleofdeath_carddirection_description_ace)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_two_actionname))
                .setText(prefs.getString(t.getString(R.string.settings_cod_two_actionname_key),
                        t.getString(R.string.circleofdeath_carddirection_two)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_two_actiontext))
                .setText(prefs.getString(t.getString(R.string.settings_cod_two_actiontext_key),
                        t.getString(R.string.circleofdeath_carddirection_description_two)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_three_actionname))
                .setText(prefs.getString(t.getString(R.string.settings_cod_three_actionname_key),
                        t.getString(R.string.circleofdeath_carddirection_three)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_three_actiontext))
                .setText(prefs.getString(t.getString(R.string.settings_cod_three_actiontext_key),
                        t.getString(R.string.circleofdeath_carddirection_description_three)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_four_actionname))
                .setText(prefs.getString(t.getString(R.string.settings_cod_four_actionname_key),
                        t.getString(R.string.circleofdeath_carddirection_four)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_four_actiontext))
                .setText(prefs.getString(t.getString(R.string.settings_cod_four_actiontext_key),
                        t.getString(R.string.circleofdeath_carddirection_description_four)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_five_actionname))
                .setText(prefs.getString(t.getString(R.string.settings_cod_five_actionname_key),
                        t.getString(R.string.circleofdeath_carddirection_five)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_five_actiontext))
                .setText(prefs.getString(t.getString(R.string.settings_cod_five_actiontext_key),
                        t.getString(R.string.circleofdeath_carddirection_description_five)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_six_actionname))
                .setText(prefs.getString(t.getString(R.string.settings_cod_six_actionname_key),
                        t.getString(R.string.circleofdeath_carddirection_six)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_six_actiontext))
                .setText(prefs.getString(t.getString(R.string.settings_cod_six_actiontext_key),
                        t.getString(R.string.circleofdeath_carddirection_description_six)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_seven_actionname))
                .setText(prefs.getString(t.getString(R.string.settings_cod_seven_actionname_key),
                        t.getString(R.string.circleofdeath_carddirection_seven)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_seven_actiontext))
                .setText(prefs.getString(t.getString(R.string.settings_cod_seven_actiontext_key),
                        t.getString(R.string.circleofdeath_carddirection_description_seven)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_eight_actionname))
                .setText(prefs.getString(t.getString(R.string.settings_cod_eight_actionname_key),
                        t.getString(R.string.circleofdeath_carddirection_eight)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_eight_actiontext))
                .setText(prefs.getString(t.getString(R.string.settings_cod_eight_actiontext_key),
                        t.getString(R.string.circleofdeath_carddirection_description_eight)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_nine_actionname))
                .setText(prefs.getString(t.getString(R.string.settings_cod_nine_actionname_key),
                        t.getString(R.string.circleofdeath_carddirection_nine)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_nine_actiontext))
                .setText(prefs.getString(t.getString(R.string.settings_cod_nine_actiontext_key),
                        t.getString(R.string.circleofdeath_carddirection_description_nine)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ten_actionname))
                .setText(prefs.getString(t.getString(R.string.settings_cod_ten_actionname_key),
                        t.getString(R.string.circleofdeath_carddirection_ten)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ten_actiontext))
                .setText(prefs.getString(t.getString(R.string.settings_cod_ten_actiontext_key),
                        t.getString(R.string.circleofdeath_carddirection_description_ten)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_jack_actionname))
                .setText(prefs.getString(t.getString(R.string.settings_cod_jack_actionname_key),
                        t.getString(R.string.circleofdeath_carddirection_jack)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_jack_actiontext))
                .setText(prefs.getString(t.getString(R.string.settings_cod_jack_actiontext_key),
                        t.getString(R.string.circleofdeath_carddirection_description_jack)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_queen_actionname))
                .setText(prefs.getString(t.getString(R.string.settings_cod_queen_actionname_key),
                        t.getString(R.string.circleofdeath_carddirection_queen)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_queen_actiontext))
                .setText(prefs.getString(t.getString(R.string.settings_cod_queen_actiontext_key),
                        t.getString(R.string.circleofdeath_carddirection_description_queen)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_king_actionname))
                .setText(prefs.getString(t.getString(R.string.settings_cod_king_actionname_key),
                        t.getString(R.string.circleofdeath_carddirection_king)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_king_actiontext))
                .setText(prefs.getString(t.getString(R.string.settings_cod_king_actiontext_key),
                        t.getString(R.string.circleofdeath_carddirection_description_king)));
        try {
            ((TextView) layout.findViewById(R.id.settingsscreen_textview_emaildeveloper))
                    .setText(String.format("     //Created by Keith MacKay\n\n     //Feedback: keith.mackay3@gmail.com\n\n     //Version: %s",
                            t.getPackageManager().getPackageInfo(t.getPackageName(), 0).versionName));
        } catch (Exception e) {
            DrinkingGamesGlobal.logException(e);
        }
        return layout;
    }

    public static void saveSettingsFromPopup(SharedPreferences.Editor prefsEditor, Context a, View layout) {
        prefsEditor.putBoolean(a.getString(R.string.setting_acesalwayslow),
                ((CheckBox) layout.findViewById(R.id.settingsscreen_acesarelow)).isChecked());
        prefsEditor.putBoolean(a.getString(R.string.settings_bizkit_insultbizkit),
                ((CheckBox) layout.findViewById(R.id.settingsscreen_bizkit_insult)).isChecked());
        prefsEditor.putString(a.getString(R.string.settings_cod_ace_actionname_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ace_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_ace_actiontext_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ace_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_two_actionname_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_two_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_two_actiontext_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_two_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_three_actionname_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_three_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_three_actiontext_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_three_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_four_actionname_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_four_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_four_actiontext_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_four_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_five_actionname_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_five_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_five_actiontext_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_five_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_six_actionname_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_six_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_six_actiontext_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_six_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_seven_actionname_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_seven_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_seven_actiontext_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_seven_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_eight_actionname_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_eight_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_eight_actiontext_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_eight_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_nine_actionname_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_nine_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_nine_actiontext_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_nine_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_ten_actionname_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ten_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_ten_actiontext_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ten_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_jack_actionname_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_jack_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_jack_actiontext_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_jack_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_queen_actionname_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_queen_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_queen_actiontext_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_queen_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_king_actionname_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_king_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.settings_cod_king_actiontext_key),
                ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_king_actiontext))
                        .getText().toString());
        prefsEditor.apply();
    }
}
