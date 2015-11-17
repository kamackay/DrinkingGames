package mobile.keithapps.drinkinggames.ridethebus;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import mobile.keithapps.CardsAndDecks.DeckManager;
import mobile.keithapps.drinkinggames.R;

/**
 * Created by Keith MacKay on 11/17/2015.
 */
public class RideTheBusMain extends AppCompatActivity {
    private DeckManager deck;
    private Button
    /**
     * Initialize the screen and rotate all of the cards to make them look
     * pretty
     *
     * @param savedInstanceState Passed by the system from the last screen
     */
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ridethebusmain);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.deck = new DeckManager();
        this.setState(State.Color);
    }

    private void setState(State s) {
        switch (s) {
            case Color:

                break;
            case High_Low:
                break;
            case Inside_Outside:
                break;
            case Suit:
                break;
        }
    }

    private void hideColorButtons() {

    }

    private enum State {Color, High_Low, Inside_Outside, Suit}
}
