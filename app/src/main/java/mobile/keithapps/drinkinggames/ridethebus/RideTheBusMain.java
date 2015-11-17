package mobile.keithapps.drinkinggames.ridethebus;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mobile.keithapps.CardsAndDecks.Card;
import mobile.keithapps.CardsAndDecks.DeckManager;
import mobile.keithapps.drinkinggames.R;

/**
 * Created by Keith MacKay on 11/17/2015.
 */
public class RideTheBusMain extends AppCompatActivity {
    private DeckManager deck;
    private Button redButton, blackButton, higherButton, lowerButton, insideButton, outsideButton;
    private TextView questionText;
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
        this.deck = new DeckManager();
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
                this.questionText.setText(R.string.ridethebus_question_color);
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
                this.questionText.setText(R.string.ridethebus_question_suit);
                break;
        }
    }

    public void guessRed(View view) {
        this.card1 = this.deck.drawCard();
        Card.Suit s = this.card1.getSuit();
        if (s == Card.Suit.HEART || s == Card.Suit.DIAMOND)
            this.setState(State.High_Low);
        else {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card1.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        }
    }

    public void guessBlack(View view) {
        this.card1 = this.deck.drawCard();
        Card.Suit s = this.card1.getSuit();
        if (s == Card.Suit.HEART || s == Card.Suit.DIAMOND) {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card1.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        } else {
            this.setState(State.High_Low);
        }
    }

    public void guessHigher(View view) {
        this.card2 = this.deck.drawCard();
        if (this.card2.getNumericValue() > this.card1.getNumericValue()) {
            this.setState(State.Inside_Outside);
        } else {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card2.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        }
    }

    public void guessLower(View view) {
        this.card2 = this.deck.drawCard();
        if (this.card2.getNumericValue() < this.card1.getNumericValue()) {
            this.setState(State.Inside_Outside);
        } else {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card2.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        }
    }

    public void guessOutside(View view) {
        this.card3 = this.deck.drawCard();
        int i1 = this.card1.getNumericValue(), i2 = this.card2.getNumericValue(), i3 = this.card3.getNumericValue();
        if (i3 > Math.max(i1, i2) || i3 < Math.min(i1, i2)) {
            this.setState(State.Suit);
        } else {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card3.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        }
    }

    public void guessInside(View view) {
        this.card3 = this.deck.drawCard();
        int i1 = this.card1.getNumericValue(), i2 = this.card2.getNumericValue(), i3 = this.card3.getNumericValue();
        if (i3 < Math.max(i1, i2) && i3 > Math.min(i1, i2)) {
            this.setState(State.Suit);
        } else {
            Toast.makeText(getApplicationContext(), "Nope, it was the " + this.card3.toString(), Toast.LENGTH_LONG).show();
            this.setState(State.Color);
        }
    }

    private enum State {Color, High_Low, Inside_Outside, Suit}
}
