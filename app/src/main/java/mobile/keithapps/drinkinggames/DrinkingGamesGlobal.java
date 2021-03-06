package mobile.keithapps.drinkinggames;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
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
     *
     * @param dieVal the value of the die needed
     * @return the drawable for the given die value
     */
    public static int getDrawableIdForDie(int dieVal, Context c) {
        int skin = c.getSharedPreferences(c.getString(R.string.text_package),
                Context.MODE_PRIVATE).getInt(c.getString(R.string.s_general_dieskin), 1);
        switch (dieVal) {
            case 1:
                switch (skin) {
                    case 1:
                        return R.drawable.die1_1;
                    case 2:
                        return R.drawable.die1_2;
                }
            case 2:
                switch (skin) {
                    case 1:
                        return R.drawable.die2_1;
                    case 2:
                        return R.drawable.die2_2;
                }
            case 3:
                switch (skin) {
                    case 1:
                        return R.drawable.die3_1;
                    case 2:
                        return R.drawable.die3_2;
                }
            case 4:
                switch (skin) {
                    case 1:
                        return R.drawable.die4_1;
                    case 2:
                        return R.drawable.die4_2;
                }
            case 5:
                switch (skin) {
                    case 1:
                        return R.drawable.die5_1;
                    case 2:
                        return R.drawable.die5_2;
                }
            case 6:
                switch (skin) {
                    case 1:
                        return R.drawable.die6_1;
                    case 2:
                        return R.drawable.die6_2;
                }
        }
        return R.drawable.die1_1;
    }

    /**
     * Log an exception
     *
     * @param e the exception, duh
     */
    public static void logException(Exception e) {
        Log.e("keithapps.drinkinggames", e.getMessage(), e);
    }

    public static View loadSettingsToLayout(final View layout, SharedPreferences prefs, final Activity t) {
        ((CheckBox) layout.findViewById(R.id.settings_bizkit_insult))
                .setChecked(prefs.getBoolean(t.getString(R.string.s_bizkit_insultbizkit), true));
        ((CheckBox) layout.findViewById(R.id.settings_acesarelow))
                .setChecked(prefs.getBoolean(t.getString(R.string.s_acesalwayslow), true));
        ((CheckBox) layout.findViewById(R.id.settings_general_showanimations))
                .setChecked(prefs.getBoolean(t.getString(R.string.s_general_showanimations), true));
        ((EditText) layout.findViewById(R.id.settings_cod_ace_actionname))
                .setText(prefs.getString(t.getString(R.string.s_cod_ace_actionname_key),
                        t.getString(R.string.cod_dir_a)));
        ((EditText) layout.findViewById(R.id.settings_cod_ace_actiontext))
                .setText(prefs.getString(t.getString(R.string.s_cod_ace_actiontext_key),
                        t.getString(R.string.cod_des_a)));
        ((EditText) layout.findViewById(R.id.settings_cod_two_actionname))
                .setText(prefs.getString(t.getString(R.string.s_cod_two_actionname_key),
                        t.getString(R.string.cod_dir_2)));
        ((EditText) layout.findViewById(R.id.settings_cod_two_actiontext))
                .setText(prefs.getString(t.getString(R.string.s_cod_two_actiontext_key),
                        t.getString(R.string.cod_des_2)));
        ((EditText) layout.findViewById(R.id.settings_cod_three_actionname))
                .setText(prefs.getString(t.getString(R.string.s_cod_three_actionname_key),
                        t.getString(R.string.cod_dir_3)));
        ((EditText) layout.findViewById(R.id.settings_cod_three_actiontext))
                .setText(prefs.getString(t.getString(R.string.s_cod_three_actiontext_key),
                        t.getString(R.string.cod_des_3)));
        ((EditText) layout.findViewById(R.id.settings_cod_four_actionname))
                .setText(prefs.getString(t.getString(R.string.s_cod_four_actionname_key),
                        t.getString(R.string.cod_dir_4)));
        ((EditText) layout.findViewById(R.id.settings_cod_four_actiontext))
                .setText(prefs.getString(t.getString(R.string.s_cod_four_actiontext_key),
                        t.getString(R.string.cod_des_4)));
        ((EditText) layout.findViewById(R.id.settings_cod_five_actionname))
                .setText(prefs.getString(t.getString(R.string.s_cod_five_actionname_key),
                        t.getString(R.string.cod_dir_5)));
        ((EditText) layout.findViewById(R.id.settings_cod_five_actiontext))
                .setText(prefs.getString(t.getString(R.string.s_cod_five_actiontext_key),
                        t.getString(R.string.cod_des_5)));
        ((EditText) layout.findViewById(R.id.settings_cod_six_actionname))
                .setText(prefs.getString(t.getString(R.string.s_cod_six_actionname_key),
                        t.getString(R.string.cod_dir_6)));
        ((EditText) layout.findViewById(R.id.settings_cod_six_actiontext))
                .setText(prefs.getString(t.getString(R.string.s_cod_six_actiontext_key),
                        t.getString(R.string.cod_des_6)));
        ((EditText) layout.findViewById(R.id.settings_cod_seven_actionname))
                .setText(prefs.getString(t.getString(R.string.s_cod_seven_actionname_key),
                        t.getString(R.string.cod_dir_7)));
        ((EditText) layout.findViewById(R.id.settings_cod_seven_actiontext))
                .setText(prefs.getString(t.getString(R.string.s_cod_seven_actiontext_key),
                        t.getString(R.string.cod_des_7)));
        ((EditText) layout.findViewById(R.id.settings_cod_eight_actionname))
                .setText(prefs.getString(t.getString(R.string.s_cod_eight_actionname_key),
                        t.getString(R.string.cod_dir_8)));
        ((EditText) layout.findViewById(R.id.settings_cod_eight_actiontext))
                .setText(prefs.getString(t.getString(R.string.s_cod_eight_actiontext_key),
                        t.getString(R.string.cod_des_8)));
        ((EditText) layout.findViewById(R.id.settings_cod_nine_actionname))
                .setText(prefs.getString(t.getString(R.string.s_cod_nine_actionname_key),
                        t.getString(R.string.cod_dir_9)));
        ((EditText) layout.findViewById(R.id.settings_cod_nine_actiontext))
                .setText(prefs.getString(t.getString(R.string.s_cod_nine_actiontext_key),
                        t.getString(R.string.cod_des_9)));
        ((EditText) layout.findViewById(R.id.settings_cod_ten_actionname))
                .setText(prefs.getString(t.getString(R.string.s_cod_ten_actionname_key),
                        t.getString(R.string.cod_dir_10)));
        ((EditText) layout.findViewById(R.id.settings_cod_ten_actiontext))
                .setText(prefs.getString(t.getString(R.string.s_cod_ten_actiontext_key),
                        t.getString(R.string.cod_des_10)));
        ((EditText) layout.findViewById(R.id.settings_cod_jack_actionname))
                .setText(prefs.getString(t.getString(R.string.s_cod_jack_actionname_key),
                        t.getString(R.string.cod_dir_j)));
        ((EditText) layout.findViewById(R.id.settings_cod_jack_actiontext))
                .setText(prefs.getString(t.getString(R.string.s_cod_jack_actiontext_key),
                        t.getString(R.string.cod_des_j)));
        ((EditText) layout.findViewById(R.id.settings_cod_queen_actionname))
                .setText(prefs.getString(t.getString(R.string.s_cod_queen_actionname_key),
                        t.getString(R.string.cod_dir_q)));
        ((EditText) layout.findViewById(R.id.settings_cod_queen_actiontext))
                .setText(prefs.getString(t.getString(R.string.s_cod_queen_actiontext_key),
                        t.getString(R.string.cod_des_q)));
        ((EditText) layout.findViewById(R.id.settings_cod_king_actionname))
                .setText(prefs.getString(t.getString(R.string.s_cod_king_actionname_key),
                        t.getString(R.string.cod_dir_k)));
        ((EditText) layout.findViewById(R.id.settings_cod_king_actiontext))
                .setText(prefs.getString(t.getString(R.string.s_cod_king_actiontext_key),
                        t.getString(R.string.cod_des_k)));
        int cardskin = prefs.getInt(t.getString(R.string.s_general_cardskin), 1),
                dieskin = prefs.getInt(t.getString(R.string.s_general_dieskin), 1);
        if (cardskin == 2) {
            ((RadioButton) layout.findViewById(R.id.settings_general_cardskin_radiobutton_1)).setChecked(false);
            ((RadioButton) layout.findViewById(R.id.settings_general_cardskin_radiobutton_2)).setChecked(true);
        } else {
            ((RadioButton) layout.findViewById(R.id.settings_general_cardskin_radiobutton_1)).setChecked(true);
            ((RadioButton) layout.findViewById(R.id.settings_general_cardskin_radiobutton_2)).setChecked(false);
        }
        if (dieskin == 2) {
            ((RadioButton) layout.findViewById(R.id.settings_general_dieskin_radiobutton_1)).setChecked(false);
            ((RadioButton) layout.findViewById(R.id.settings_general_dieskin_radiobutton_2)).setChecked(true);
        } else {
            ((RadioButton) layout.findViewById(R.id.settings_general_dieskin_radiobutton_1)).setChecked(true);
            ((RadioButton) layout.findViewById(R.id.settings_general_dieskin_radiobutton_2)).setChecked(false);
        }
        View.OnClickListener cardClick1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RadioButton) layout.findViewById(R.id.settings_general_cardskin_radiobutton_1)).setChecked(true);
                ((RadioButton) layout.findViewById(R.id.settings_general_cardskin_radiobutton_2)).setChecked(false);
            }
        }, cardClick2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RadioButton) layout.findViewById(R.id.settings_general_cardskin_radiobutton_1)).setChecked(false);
                ((RadioButton) layout.findViewById(R.id.settings_general_cardskin_radiobutton_2)).setChecked(true);
            }
        }, dieClick1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RadioButton) layout.findViewById(R.id.settings_general_dieskin_radiobutton_1)).setChecked(true);
                ((RadioButton) layout.findViewById(R.id.settings_general_dieskin_radiobutton_2)).setChecked(false);
            }
        }, dieClick2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RadioButton) layout.findViewById(R.id.settings_general_dieskin_radiobutton_1)).setChecked(false);
                ((RadioButton) layout.findViewById(R.id.settings_general_dieskin_radiobutton_2)).setChecked(true);
            }
        };
        layout.findViewById(R.id.settings_general_cardskin_imageview_1).setOnClickListener(cardClick1);
        layout.findViewById(R.id.settings_general_cardskin_radiobutton_1).setOnClickListener(cardClick1);
        layout.findViewById(R.id.settings_general_cardskin_radiobutton_2).setOnClickListener(cardClick2);
        layout.findViewById(R.id.settings_general_cardskin_imageview_2).setOnClickListener(cardClick2);
        layout.findViewById(R.id.settings_general_dieskin_imageview_1).setOnClickListener(dieClick1);
        layout.findViewById(R.id.settings_general_dieskin_radiobutton_1).setOnClickListener(dieClick1);
        layout.findViewById(R.id.settings_general_dieskin_imageview_2).setOnClickListener(dieClick2);
        layout.findViewById(R.id.settings_general_dieskin_radiobutton_2).setOnClickListener(dieClick2);
        layout.findViewById(R.id.settings_tabs_general).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.findViewById(R.id.settings_tabs_circleofdeath).setBackgroundColor(ContextCompat.getColor(t, R.color.transparent));
                layout.findViewById(R.id.settings_tabs_ridethebus).setBackgroundColor(ContextCompat.getColor(t, R.color.transparent));
                layout.findViewById(R.id.settings_tabs_bizkit).setBackgroundColor(ContextCompat.getColor(t, R.color.transparent));
                layout.findViewById(R.id.settings_tabs_general).setBackgroundResource(R.drawable.buttonbackground_allsides_small);
                layout.findViewById(R.id.settings_circleofdeath_root).setVisibility(View.GONE);
                layout.findViewById(R.id.settings_ridethebus_root).setVisibility(View.GONE);
                layout.findViewById(R.id.settings_bizkit_root).setVisibility(View.GONE);
                layout.findViewById(R.id.settings_general_root).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_general_cardskin_root).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_general_dieskin_root).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_general_showanimations).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_scrollview_root).scrollTo(0, 0);
            }
        });
        layout.findViewById(R.id.settings_tabs_circleofdeath).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.findViewById(R.id.settings_tabs_circleofdeath).setBackgroundResource(R.drawable.buttonbackground_allsides_small);
                layout.findViewById(R.id.settings_tabs_ridethebus).setBackgroundColor(ContextCompat.getColor(t, R.color.transparent));
                layout.findViewById(R.id.settings_tabs_bizkit).setBackgroundColor(ContextCompat.getColor(t, R.color.transparent));
                layout.findViewById(R.id.settings_tabs_general).setBackgroundColor(ContextCompat.getColor(t, R.color.transparent));
                layout.findViewById(R.id.settings_circleofdeath_root).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_ridethebus_root).setVisibility(View.GONE);
                layout.findViewById(R.id.settings_bizkit_root).setVisibility(View.GONE);
                layout.findViewById(R.id.settings_general_root).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_general_cardskin_root).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_general_dieskin_root).setVisibility(View.GONE);
                layout.findViewById(R.id.settings_general_showanimations).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_scrollview_root).scrollTo(0, 0);
            }
        });
        layout.findViewById(R.id.settings_tabs_ridethebus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.findViewById(R.id.settings_tabs_circleofdeath).setBackgroundColor(ContextCompat.getColor(t, R.color.transparent));
                layout.findViewById(R.id.settings_tabs_ridethebus).setBackgroundResource(R.drawable.buttonbackground_allsides_small);
                layout.findViewById(R.id.settings_tabs_bizkit).setBackgroundColor(ContextCompat.getColor(t, R.color.transparent));
                layout.findViewById(R.id.settings_tabs_general).setBackgroundColor(ContextCompat.getColor(t, R.color.transparent));
                layout.findViewById(R.id.settings_circleofdeath_root).setVisibility(View.GONE);
                layout.findViewById(R.id.settings_ridethebus_root).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_bizkit_root).setVisibility(View.GONE);
                layout.findViewById(R.id.settings_general_root).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_general_cardskin_root).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_general_dieskin_root).setVisibility(View.GONE);
                layout.findViewById(R.id.settings_general_showanimations).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_scrollview_root).scrollTo(0, 0);
            }
        });
        layout.findViewById(R.id.settings_tabs_bizkit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.findViewById(R.id.settings_tabs_circleofdeath).setBackgroundColor(ContextCompat.getColor(t, R.color.transparent));
                layout.findViewById(R.id.settings_tabs_ridethebus).setBackgroundColor(ContextCompat.getColor(t, R.color.transparent));
                layout.findViewById(R.id.settings_tabs_bizkit).setBackgroundResource(R.drawable.buttonbackground_allsides_small);
                layout.findViewById(R.id.settings_tabs_general).setBackgroundColor(ContextCompat.getColor(t, R.color.transparent));
                layout.findViewById(R.id.settings_circleofdeath_root).setVisibility(View.GONE);
                layout.findViewById(R.id.settings_ridethebus_root).setVisibility(View.GONE);
                layout.findViewById(R.id.settings_bizkit_root).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_general_root).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_general_cardskin_root).setVisibility(View.GONE);
                layout.findViewById(R.id.settings_general_dieskin_root).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.settings_general_showanimations).setVisibility(View.GONE);
                layout.findViewById(R.id.settings_scrollview_root).scrollTo(0, 0);
            }
        });//Yay, Convolution!
        try {
            ((TextView) layout.findViewById(R.id.settings_textview_emaildeveloper))
                    .setText(String.format("     //Created by Keith MacKay\n\n     //Feedback: keith.mackay3@gmail.com\n\n     //Version: %s",
                            t.getPackageManager().getPackageInfo(t.getPackageName(), 0).versionName));
        } catch (Exception e) {
            DrinkingGamesGlobal.logException(e);
        }
        return layout;
    }

    public static void saveSettingsFromPopup(SharedPreferences.Editor prefsEditor, Context a, View layout) {
        prefsEditor.putBoolean(a.getString(R.string.s_acesalwayslow),
                ((CheckBox) layout.findViewById(R.id.settings_acesarelow)).isChecked());
        prefsEditor.putBoolean(a.getString(R.string.s_bizkit_insultbizkit),
                ((CheckBox) layout.findViewById(R.id.settings_bizkit_insult)).isChecked());
        prefsEditor.putBoolean(a.getString(R.string.s_general_showanimations),
                ((CheckBox) layout.findViewById(R.id.settings_general_showanimations)).isChecked());
        if (((RadioButton) layout.findViewById(R.id.settings_general_cardskin_radiobutton_2)).isChecked())
            prefsEditor.putInt(a.getString(R.string.s_general_cardskin), 2);
        else prefsEditor.putInt(a.getString(R.string.s_general_cardskin), 1);
        if (((RadioButton) layout.findViewById(R.id.settings_general_dieskin_radiobutton_2)).isChecked())
            prefsEditor.putInt(a.getString(R.string.s_general_dieskin), 2);
        else prefsEditor.putInt(a.getString(R.string.s_general_dieskin), 1);
        prefsEditor.putString(a.getString(R.string.s_cod_ace_actionname_key),
                ((EditText) layout.findViewById(R.id.settings_cod_ace_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_ace_actiontext_key),
                ((EditText) layout.findViewById(R.id.settings_cod_ace_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_two_actionname_key),
                ((EditText) layout.findViewById(R.id.settings_cod_two_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_two_actiontext_key),
                ((EditText) layout.findViewById(R.id.settings_cod_two_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_three_actionname_key),
                ((EditText) layout.findViewById(R.id.settings_cod_three_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_three_actiontext_key),
                ((EditText) layout.findViewById(R.id.settings_cod_three_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_four_actionname_key),
                ((EditText) layout.findViewById(R.id.settings_cod_four_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_four_actiontext_key),
                ((EditText) layout.findViewById(R.id.settings_cod_four_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_five_actionname_key),
                ((EditText) layout.findViewById(R.id.settings_cod_five_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_five_actiontext_key),
                ((EditText) layout.findViewById(R.id.settings_cod_five_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_six_actionname_key),
                ((EditText) layout.findViewById(R.id.settings_cod_six_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_six_actiontext_key),
                ((EditText) layout.findViewById(R.id.settings_cod_six_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_seven_actionname_key),
                ((EditText) layout.findViewById(R.id.settings_cod_seven_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_seven_actiontext_key),
                ((EditText) layout.findViewById(R.id.settings_cod_seven_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_eight_actionname_key),
                ((EditText) layout.findViewById(R.id.settings_cod_eight_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_eight_actiontext_key),
                ((EditText) layout.findViewById(R.id.settings_cod_eight_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_nine_actionname_key),
                ((EditText) layout.findViewById(R.id.settings_cod_nine_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_nine_actiontext_key),
                ((EditText) layout.findViewById(R.id.settings_cod_nine_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_ten_actionname_key),
                ((EditText) layout.findViewById(R.id.settings_cod_ten_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_ten_actiontext_key),
                ((EditText) layout.findViewById(R.id.settings_cod_ten_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_jack_actionname_key),
                ((EditText) layout.findViewById(R.id.settings_cod_jack_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_jack_actiontext_key),
                ((EditText) layout.findViewById(R.id.settings_cod_jack_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_queen_actionname_key),
                ((EditText) layout.findViewById(R.id.settings_cod_queen_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_queen_actiontext_key),
                ((EditText) layout.findViewById(R.id.settings_cod_queen_actiontext))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_king_actionname_key),
                ((EditText) layout.findViewById(R.id.settings_cod_king_actionname))
                        .getText().toString());
        prefsEditor.putString(a.getString(R.string.s_cod_king_actiontext_key),
                ((EditText) layout.findViewById(R.id.settings_cod_king_actiontext))
                        .getText().toString());
        prefsEditor.apply();
    }
}
