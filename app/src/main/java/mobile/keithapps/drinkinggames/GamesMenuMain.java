package mobile.keithapps.drinkinggames;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mobile.keithapps.drinkinggames.biscuit.BizkitMain;
import mobile.keithapps.drinkinggames.circleofdeath.CircleOfDeathMain;
import mobile.keithapps.drinkinggames.ridethebus.RideTheBusMain;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GamesMenuMain extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_menu);
        if (Build.VERSION.SDK_INT >= 21) this.setTheme(R.style.Theme_FullscreenTheme_MaterialDark);
        try {
            ((TextView) this.findViewById(R.id.menu_textview_emaildeveloper))
                    .setText(String.format("     //Created by Keith MacKay\n\n     //Feedback: keith.mackay3@gmail.com\n\n     //Version: %s",
                            this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName));
        } catch (Exception e) {
            DrinkingGamesGlobal.logException(e);
        }
    }

    /**
     * Open Circle of Death
     *
     * @param view the button
     */
    public void openCOD(View view) {
        try {
            Intent i = new Intent(getApplicationContext(), CircleOfDeathMain.class);
            startActivity(i);
        } catch (Exception e) {
            DrinkingGamesGlobal.logException(e);
        }
    }

    /**
     * Open Ride the Bus
     *
     * @param view the button
     */
    public void openRTB(View view) {
        try {
            Intent i = new Intent(getApplicationContext(), RideTheBusMain.class);
            startActivity(i);
        } catch (Exception e) {
            DrinkingGamesGlobal.logException(e);
        }
    }

    /**
     * Open Bizkit
     *
     * @param view the button
     */
    public void openBizkit(View view) {
        try {
            Intent i = new Intent(getApplicationContext(), BizkitMain.class);
            startActivity(i);
        } catch (Exception e) {
            DrinkingGamesGlobal.logException(e);
        }
    }

    /**
     * Make the options menu using the xml file
     *
     * @param menu Passed by the system
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gamesmenu_menu, menu);
        return true;
    }

    /**
     * @param item Menu Item that was selected
     * @return pretty much always true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.gamesmenu_settings:
                showSettingsPopup();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Show the settings popup
     */
    private void showSettingsPopup() {
        final AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        SharedPreferences prefs = getSharedPreferences(getString(R.string.text_package), Context.MODE_PRIVATE);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View l = inflater.inflate(R.layout.popup_settings,
                (ViewGroup) findViewById(R.id.settings_scrollview_root));
        final View layout = DrinkingGamesGlobal.loadSettingsToLayout(l, prefs, this);
        layout.findViewById(R.id.settings_tabs_general).callOnClick();
        imageDialog.setView(layout);
        imageDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        imageDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                SharedPreferences.Editor prefsEditor = getSharedPreferences(
                        getString(R.string.text_package), Context.MODE_PRIVATE).edit();
                DrinkingGamesGlobal.saveSettingsFromPopup(prefsEditor, getApplicationContext(), layout);
                prefsEditor.apply();
            }
        });
        final AlertDialog dialog = imageDialog.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                        .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setAllCaps(false);
            }
        });
        dialog.show();
    }
}
