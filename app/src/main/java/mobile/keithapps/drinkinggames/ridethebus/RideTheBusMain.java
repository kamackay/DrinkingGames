package mobile.keithapps.drinkinggames.ridethebus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
    private RelativeLayout redButton;
    private RelativeLayout blackButton;
    private Button higherButton;
    private Button lowerButton;
    private Button insideButton;
    private Button outsideButton;
    private RelativeLayout diamondButton;
    private RelativeLayout heartButton;
    private RelativeLayout clubButton;
    private RelativeLayout spadeButton;
    private TextView questionText;
    private TextView warningAceIsLow;
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


        if (Build.VERSION.SDK_INT >= 21)
            this.setTheme(R.style.Theme_FullscreenTheme_MaterialDark);

        this.deck = new CardDeck();
        this.cardView1 = (ImageView) findViewById(R.id.ridethebus_uppercardsview_card1);
        this.cardView2 = (ImageView) findViewById(R.id.ridethebus_uppercardsview_card2);
        this.cardView3 = (ImageView) findViewById(R.id.ridethebus_uppercardsview_card3);
        this.cardView4 = (ImageView) findViewById(R.id.ridethebus_uppercardsview_card4);
        this.blackButton = (RelativeLayout) findViewById(R.id.ridethebus_blackbutton);
        this.redButton = (RelativeLayout) findViewById(R.id.ridethebus_redbutton);
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
        this.warningAceIsLow = (TextView) findViewById(R.id.ridethebus_aceislow_warning);
        this.setState(State.Color);
    }

    public void setState(State s) {
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
        if (this.card1 != null && this.card1.getNumericValue() == 1)
            this.warningAceIsLow.setVisibility(View.VISIBLE);
        else if (this.card2 != null && this.card2.getNumericValue() == 1)
            this.warningAceIsLow.setVisibility(View.VISIBLE);
        else if (this.card3 != null && this.card3.getNumericValue() == 1)
            this.warningAceIsLow.setVisibility(View.VISIBLE);
        else if (this.card4 != null && this.card4.getNumericValue() == 1)
            this.warningAceIsLow.setVisibility(View.VISIBLE);
        else this.warningAceIsLow.setVisibility(View.INVISIBLE);
    }

    public void guessRed(View view) {
        this.card1 = this.deck.drawRandom();
        Card.Suit s = this.card1.getSuit();
        if (s == Card.Suit.HEART || s == Card.Suit.DIAMOND)
            this.setState(State.High_Low);
        else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.rtb_popup_layout, (ViewGroup) findViewById(R.id.layout_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card1.toString(), "(You guessed Red)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            imageView.setImageResource(this.card1.getImageId());
            imageDialog.setView(layout);
            imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    setState(State.Color);
                }
            });
            final AlertDialog dialog = imageDialog.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                            .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
                }
            });
            dialog.show();
        }
    }

    public void guessBlack(View view) {
        this.card1 = this.deck.drawRandom();
        Card.Suit s = this.card1.getSuit();
        if (s == Card.Suit.HEART || s == Card.Suit.DIAMOND) {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.rtb_popup_layout, (ViewGroup) findViewById(R.id.layout_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card1.toString(), "(You guessed Black)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            imageView.setImageResource(this.card1.getImageId());
            imageDialog.setView(layout);
            imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    setState(State.Color);
                }
            });
            final AlertDialog dialog = imageDialog.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                            .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
                }
            });
            dialog.show();
        } else {
            this.setState(State.High_Low);
        }
    }

    public void guessHigher(View view) {
        this.card2 = this.deck.drawRandom();
        if (this.card2.getNumericValue() > this.card1.getNumericValue()) {
            this.setState(State.Inside_Outside);
        } else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.rtb_popup_layout, (ViewGroup) findViewById(R.id.layout_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card2.toString(), "(You guessed Higher)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            ((ImageView) layout.findViewById(R.id.rtb_popup_card1)).setImageResource(this.card1.getImageId());
            imageView.setImageResource(this.card2.getImageId());
            imageDialog.setView(layout);
            imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    setState(State.Color);
                }
            });
            final AlertDialog dialog = imageDialog.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                            .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
                }
            });
            dialog.show();
        }
    }

    public void guessLower(View view) {
        this.card2 = this.deck.drawRandom();
        if (this.card2.getNumericValue() < this.card1.getNumericValue()) {
            this.setState(State.Inside_Outside);
        } else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.rtb_popup_layout, (ViewGroup) findViewById(R.id.layout_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card2.toString(), "(You guessed Lower)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            ((ImageView) layout.findViewById(R.id.rtb_popup_card1)).setImageResource(this.card1.getImageId());
            imageView.setImageResource(this.card2.getImageId());
            imageDialog.setView(layout);
            imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    setState(State.Color);
                }
            });
            final AlertDialog dialog = imageDialog.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                            .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
                }
            });
            dialog.show();
        }
    }

    public void guessOutside(View view) {
        this.card3 = this.deck.drawRandom();
        int i1 = this.card1.getNumericValue(), i2 = this.card2.getNumericValue(), i3 = this.card3.getNumericValue();
        if (i3 > Math.max(i1, i2) || i3 < Math.min(i1, i2)) {
            this.setState(State.Suit);
        } else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.rtb_popup_layout, (ViewGroup) findViewById(R.id.layout_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card3.toString(), "(You guessed Outside)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            ((ImageView) layout.findViewById(R.id.rtb_popup_card1)).setImageResource(this.card1.getImageId());
            ((ImageView) layout.findViewById(R.id.rtb_popup_card2)).setImageResource(this.card2.getImageId());
            imageView.setImageResource(this.card3.getImageId());
            imageDialog.setView(layout);
            imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    setState(State.Color);
                }
            });
            final AlertDialog dialog = imageDialog.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                            .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
                }
            });
            dialog.show();
        }
    }

    public void guessInside(View view) {
        this.card3 = this.deck.drawRandom();
        int i1 = this.card1.getNumericValue(), i2 = this.card2.getNumericValue(), i3 = this.card3.getNumericValue();
        if (i3 < Math.max(i1, i2) && i3 > Math.min(i1, i2)) {
            this.setState(State.Suit);
        } else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.rtb_popup_layout, (ViewGroup) findViewById(R.id.layout_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card3.toString(), "(You guessed Inside)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            ((ImageView) layout.findViewById(R.id.rtb_popup_card1)).setImageResource(this.card1.getImageId());
            ((ImageView) layout.findViewById(R.id.rtb_popup_card2)).setImageResource(this.card2.getImageId());
            imageView.setImageResource(this.card3.getImageId());
            imageDialog.setView(layout);
            imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    setState(State.Color);
                }
            });
            final AlertDialog dialog = imageDialog.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                            .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
                }
            });
            dialog.show();
        }
    }

    public void guessClub(View view) {
        this.card4 = this.deck.drawRandom();
        Card.Suit s = this.card4.getSuit();
        if (s == Card.Suit.CLUB) this.setState(State.Won);
        else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.rtb_popup_layout, (ViewGroup) findViewById(R.id.layout_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card4.toString(), "(You guessed Clubs)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            try {
                if (this.card1 != null)
                    ((ImageView) layout.findViewById(R.id.rtb_popup_card1)).setImageResource(this.card1.getImageId());
                if (this.card2 != null)
                    ((ImageView) layout.findViewById(R.id.rtb_popup_card2)).setImageResource(this.card2.getImageId());
                if (this.card3 != null)
                    ((ImageView) layout.findViewById(R.id.rtb_popup_card3)).setImageResource(this.card3.getImageId());
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            imageView.setImageResource(this.card4.getImageId());
            imageDialog.setView(layout);
            imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    setState(State.Color);
                }
            });
            final AlertDialog dialog = imageDialog.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                            .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
                }
            });
            dialog.show();
        }
    }

    public void guessHeart(View view) {
        this.card4 = this.deck.drawRandom();
        Card.Suit s = this.card4.getSuit();
        if (s == Card.Suit.HEART) this.setState(State.Won);
        else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.rtb_popup_layout, (ViewGroup) findViewById(R.id.layout_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card4.toString(), "(You guessed Hearts)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            try {
                if (this.card1 != null)
                    ((ImageView) layout.findViewById(R.id.rtb_popup_card1)).setImageResource(this.card1.getImageId());
                if (this.card2 != null)
                    ((ImageView) layout.findViewById(R.id.rtb_popup_card2)).setImageResource(this.card2.getImageId());
                if (this.card3 != null)
                    ((ImageView) layout.findViewById(R.id.rtb_popup_card3)).setImageResource(this.card3.getImageId());
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            imageView.setImageResource(this.card4.getImageId());
            imageDialog.setView(layout);
            imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    setState(State.Color);
                }
            });
            final AlertDialog dialog = imageDialog.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                            .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
                }
            });
            dialog.show();
        }
    }

    public void guessDiamond(View view) {
        this.card4 = this.deck.drawRandom();
        Card.Suit s = this.card4.getSuit();
        if (s == Card.Suit.DIAMOND) this.setState(State.Won);
        else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.rtb_popup_layout, (ViewGroup) findViewById(R.id.layout_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card4.toString(), "(You guessed Diamonds)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            try {
                if (this.card1 != null)
                    ((ImageView) layout.findViewById(R.id.rtb_popup_card1)).setImageResource(this.card1.getImageId());
                if (this.card2 != null)
                    ((ImageView) layout.findViewById(R.id.rtb_popup_card2)).setImageResource(this.card2.getImageId());
                if (this.card3 != null)
                    ((ImageView) layout.findViewById(R.id.rtb_popup_card3)).setImageResource(this.card3.getImageId());
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            imageView.setImageResource(this.card4.getImageId());
            imageDialog.setView(layout);
            imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    setState(State.Color);
                }
            });
            final AlertDialog dialog = imageDialog.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                            .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
                }
            });
            dialog.show();
        }
    }

    public void guessSpade(View view) {
        this.card4 = this.deck.drawRandom();
        Card.Suit s = this.card4.getSuit();
        if (s == Card.Suit.SPADE) this.setState(State.Won);
        else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.rtb_popup_layout, (ViewGroup) findViewById(R.id.layout_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card4.toString(), "(You guessed Spades)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            try {
                if (this.card1 != null)
                    ((ImageView) layout.findViewById(R.id.rtb_popup_card1)).setImageResource(this.card1.getImageId());
                if (this.card2 != null)
                    ((ImageView) layout.findViewById(R.id.rtb_popup_card2)).setImageResource(this.card2.getImageId());
                if (this.card3 != null)
                    ((ImageView) layout.findViewById(R.id.rtb_popup_card3)).setImageResource(this.card3.getImageId());
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            imageView.setImageResource(this.card4.getImageId());
            imageDialog.setView(layout);
            imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    setState(State.Color);
                }
            });
            final AlertDialog dialog = imageDialog.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                            .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
                }
            });
            dialog.show();
        }
    }

    public void restart(View view) {
        this.deck.reset();
        this.setState(State.Color);
        try {
            View root = findViewById(R.id.rtb_root);
/**            Snackbar.make(root, "This is main activity", Snackbar.LENGTH_LONG)
 .setAction("CLOSE", new View.OnClickListener() {
@Override public void onClick(View view) {

}
})
 .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
 .show();*/
        } catch (Exception e) {
            //Do Nothing
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

    public enum State {Color, High_Low, Inside_Outside, Suit, Won}
}
