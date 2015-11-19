package mobile.keithapps.drinkinggames;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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


        if (Build.VERSION.SDK_INT >= 21)
            this.setTheme(R.style.Theme_FullscreenTheme_MaterialDark);
    }

    public void openCOD(View view) {
        Intent i = new Intent(getApplicationContext(), CircleOfDeathMain.class);
        startActivity(i);
    }

    public void openRTB(View view) {
        Intent i = new Intent(getApplicationContext(), RideTheBusMain.class);
        startActivity(i);
    }

    public void openBizkit(View v) {
        Intent i = new Intent(getApplicationContext(), BizkitMain.class);
        startActivity(i);
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
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.gamesmenu_settings:
                Intent i = new Intent(getApplicationContext(), SettingsMain.class);
                startActivity(i);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
