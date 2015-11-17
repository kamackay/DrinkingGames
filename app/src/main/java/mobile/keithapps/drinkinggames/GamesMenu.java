package mobile.keithapps.drinkinggames;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mobile.keithapps.drinkinggames.circleofdeath.CircleOfDeathMain;
import mobile.keithapps.drinkinggames.ridethebus.RideTheBusMain;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GamesMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_menu);
    }

    public void openCOD(View view) {
        Intent i = new Intent(this, CircleOfDeathMain.class);
        startActivity(i);
    }

    public void openRTB(View view) {
        Intent i = new Intent(this, RideTheBusMain.class);
        startActivity(i);
    }
}
