package mobile.keithapps.drinkinggames.ridethebus;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import mobile.keithapps.CardsAndDecks.Card;
import mobile.keithapps.CardsAndDecks.CardDeck;
import mobile.keithapps.drinkinggames.R;

/**
 * Created by Keith MacKay on 11/17/2015.
 */
public class RideTheBusMain extends AppCompatActivity {
    private CardDeck deck;
    private Button redButton;
    private Button blackButton;
    private Button higherButton;
    private Button lowerButton;
    private Button insideButton;
    private Button outsideButton;
    private RelativeLayout diamondButton;
    private RelativeLayout heartButton;
    private RelativeLayout clubButton;
    private RelativeLayout spadeButton;
    private TextView questionText;
    private Button restartButton;
    private ImageView cardView1, cardView2, cardView3, cardView4;
    private Card card1, card2, card3, card4;

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
        this.deck = new CardDeck();
        this.cardView1 = (ImageView) findViewById(R.id.ridethebus_uppercardsview_card1);
        this.cardView2 = (ImageView) findViewById(R.id.ridethebus_uppercardsview_card2);
        this.cardView3 = (ImageView) findViewById(R.id.ridethebus_uppercardsview_card3);
        this.cardView4 = (ImageView) findViewById(R.id.ridethebus_uppercardsview_card4);
        this.blackButton = (Button) findViewById(R.id.ridethebus_blackbutton);
        this.redButton = (Button) findViewById(R.id.ridethebus_redbutton);
        this.higherButton = (Button) findViewById(R.id.ridethebus_higherbutton);
        this.lowerButton = (Button) findViewById(R.id.ridethebus_lowerbutton);
        this.questionText = (TextView) findViewById(R.id.ridethebus_questiontext);
        this.insideButton = (Button) findViewById(R.id.ridethebus_insidebutton);
        this.outsideButton = (Button) findViewById(R.id.ridethebus_outsidebutton);
        this.clubButton = (RelativeLayout) findViewById(R.id.ridethebus_clubbutton);
        this.diamondButton = (RelativeLayout) findViewById(R.id.ridethebus_diamondbutton);
        this.spadeButton = (RelativeLayout) findViewById(R.id.ridethebus_spadebutton);
        this.heartButton = (RelativeLayout) findViewById(R.id.ridethebus_heartbutton);
        this.restartButton = (Button) findViewById(R.id.ridethebus_restartbutton);
        this.setState(State.Color);
    }

    private void setState(State s) {
        switch (s) {
            case Color:
                this.card1 = this.card2 = this.card3 = this.card4 = null;
                this.cardView1.setVisibility(View.INVISIBLE);
                this.cardView2.setVisibility(View.INVISIBLE);
                this.cardView3.setVisibility(View.INVISIBLE);
                this.cardView4.setVisibility(View.INVISIBLE);
                this.blackButton.setVisibility(View.VISIBLE);
                this.redButton.setVisibility(View.VISIBLE);
                this.higherButton.setVisibility(View.GONE);
                this.lowerButton.setVisibility(View.GONE);
                this.insideButton.setVisibility(View.GONE);
                this.outsideButton.setVisibility(View.GONE);
                this.heartButton.setVisibility(View.GONE);
                this.clubButton.setVisibility(View.GONE);
                this.spadeButton.setVisibility(View.GONE);
                this.diamondButton.setVisibility(View.GONE);
                this.questionText.setText(R.string.ridethebus_question_color);
                this.restartButton.setVisibility(View.GONE);
                break;
            case High_Low:
                this.cardView1.setVisibility(View.VISIBLE);
                this.cardView1.setImageResource(this.card1.getImageId());
                this.blackButton.setVisibility(View.GONE);
                this.redButton.setVisibility(View.GONE);
                this.higherButton.setVisibility(View.VISIBLE);
                this.lowerButton.setVisibility(View.VISIBLE);
                this.questionText.setText(R.string.ridethebus_question_highlow);
                break;
            case Inside_Outside:
                this.cardView2.setVisibility(View.VISIBLE);
                this.cardView2.setImageResource(this.card2.getImageId());
                this.outsideButton.setVisibility(View.VISIBLE);
                this.insideButton.setVisibility(View.VISIBLE);
                this.higherButton.setVisibility(View.GONE);
                this.lowerButton.setVisibility(View.GONE);
                this.questionText.setText(R.string.ridethebus_question_inout);
                break;
            case Suit:
                this.cardView3.setVisibility(View.VISIBLE);
                this.cardView3.setImageResource(this.card3.getImageId());
                this.outsideButton.setVisibility(View.GONE);
                this.insideButton.setVisibility(View.GONE);
                this.heartButton.setVisibility(View.VISIBLE);
                this.clubButton.setVisibility(View.VISIBLE);
                this.spadeButton.setVisibility(View.VISIBLE);
                this.diamondButton.setVisibility(View.VISIBLE);
                this.questionText.setText(R.string.ridethebus_question_suit);
                break;
            case Won:
                this.cardView4.setVisibility(View.VISIBLE);
                this.cardView4.setImageResource(this.card4.getImageId());
                this.questionText.setText(R.string.text_youwon);
                this.heartButton.setVisibility(View.GONE);
                this.clubButton.setVisibility(View.GONE);
                this.spadeButton.setVisibility(View.GONE);
                this.diamondButton.setVisibility(View.GONE);
                this.restartButton.setVisibility(View.VISIBLE);
        }
    }

    public void guessRed(View view) {
        this.card1 = this.deck.drawRandom();
        Card.Suit s = this.card1.getSuit();
        if (s == Card.Suit.HEART || s == Card.Suit.DIAMOND)
            this.setState(State.High_Low);
        else {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card1.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        }
    }

    public void guessBlack(View view) {
        this.card1 = this.deck.drawRandom();
        Card.Suit s = this.card1.getSuit();
        if (s == Card.Suit.HEART || s == Card.Suit.DIAMOND) {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card1.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        } else {
            this.setState(State.High_Low);
        }
    }

    public void guessHigher(View view) {
        this.card2 = this.deck.drawRandom();
        if (this.card2.getNumericValue() > this.card1.getNumericValue()) {
            this.setState(State.Inside_Outside);
        } else {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card2.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        }
    }

    public void guessLower(View view) {
        this.card2 = this.deck.drawRandom();
        if (this.card2.getNumericValue() < this.card1.getNumericValue()) {
            this.setState(State.Inside_Outside);
        } else {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card2.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        }
    }

    public void guessOutside(View view) {
        this.card3 = this.deck.drawRandom();
        int i1 = this.card1.getNumericValue(), i2 = this.card2.getNumericValue(), i3 = this.card3.getNumericValue();
        if (i3 > Math.max(i1, i2) || i3 < Math.min(i1, i2)) {
            this.setState(State.Suit);
        } else {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card3.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        }
    }

    public void guessInside(View view) {
        this.card3 = this.deck.drawRandom();
        int i1 = this.card1.getNumericValue(), i2 = this.card2.getNumericValue(), i3 = this.card3.getNumericValue();
        if (i3 < Math.max(i1, i2) && i3 > Math.min(i1, i2)) {
            this.setState(State.Suit);
        } else {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card3.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        }
    }

    public void guessClub(View view) {
        this.card4 = this.deck.drawRandom();
        Card.Suit s = this.card4.getSuit();
        if (s == Card.Suit.CLUB) this.setState(State.Won);
        else {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card4.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        }
    }

    public void guessHeart(View view) {
        this.card4 = this.deck.drawRandom();
        Card.Suit s = this.card4.getSuit();
        if (s == Card.Suit.HEART) this.setState(State.Won);
        else {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card4.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        }
    }

    public void guessDiamond(View view) {
        this.card4 = this.deck.drawRandom();
        Card.Suit s = this.card4.getSuit();
        if (s == Card.Suit.DIAMOND) this.setState(State.Won);
        else {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card4.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        }
    }

    public void guessSpade(View view) {
        this.card4 = this.deck.drawRandom();
        Card.Suit s = this.card4.getSuit();
        if (s == Card.Suit.SPADE) this.setState(State.Won);
        else {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card4.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        }
    }

    public void restart(View view) {
        this.deck.reset();
        this.setState(State.Color);
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
        inflater.inflate(R.menu.ridethebus_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.circlemain_resetcards:
                this.restart(null);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private enum State {Color, High_Low, Inside_Outside, Suit, Won}
}
