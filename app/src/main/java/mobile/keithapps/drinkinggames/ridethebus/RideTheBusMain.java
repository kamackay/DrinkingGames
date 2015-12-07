package mobile.keithapps.drinkinggames.ridethebus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import mobile.keithapps.CardsAndDecks.Card;
import mobile.keithapps.CardsAndDecks.CardDeck;
import mobile.keithapps.customviews.CardView;
import mobile.keithapps.drinkinggames.DrinkingGamesGlobal;
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
    private CardView cv;
    private LinearLayout suitsButtons;
    private TextView questionText;
    private TextView warningAce;
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
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) this.setTheme(R.style.Theme_FullscreenTheme_MaterialDark);
        this.deck = new CardDeck();
        this.blackButton = (RelativeLayout) findViewById(R.id.ridethebus_blackbutton);
        this.redButton = (RelativeLayout) findViewById(R.id.ridethebus_redbutton);
        this.higherButton = (Button) findViewById(R.id.ridethebus_higherbutton);
        this.lowerButton = (Button) findViewById(R.id.ridethebus_lowerbutton);
        this.questionText = (TextView) findViewById(R.id.ridethebus_questiontext);
        this.insideButton = (Button) findViewById(R.id.ridethebus_insidebutton);
        this.outsideButton = (Button) findViewById(R.id.ridethebus_outsidebutton);
        this.suitsButtons = (LinearLayout) findViewById(R.id.rtb_buttons_suits);
        this.restartButton = (Button) findViewById(R.id.ridethebus_restartbutton);
        this.warningAce = (TextView) findViewById(R.id.rtb_acewarning);
        this.cardView1 = (ImageView) findViewById(R.id.ridethebus_uppercardsview_card1);
        this.cardView2 = (ImageView) findViewById(R.id.ridethebus_uppercardsview_card2);
        this.cardView3 = (ImageView) findViewById(R.id.ridethebus_uppercardsview_card3);
        this.cardView4 = (ImageView) findViewById(R.id.ridethebus_uppercardsview_card4);
        this.cv = (CardView) findViewById(R.id.rtb_cardview_main);
        this.setState(State.Color);
    }

    /**
     * FSM-like framework to control the state of the game
     *
     * @param s the state that the game is in
     */
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
                this.suitsButtons.setVisibility(View.GONE);
                this.questionText.setText(R.string.rtb_question_color);
                this.restartButton.setVisibility(View.GONE);
                break;
            case High_Low:
                this.cardView1.setVisibility(View.VISIBLE);
                this.cardView1.setImageResource(this.card1.getImageId());
                this.blackButton.setVisibility(View.GONE);
                this.redButton.setVisibility(View.GONE);
                this.higherButton.setVisibility(View.VISIBLE);
                this.lowerButton.setVisibility(View.VISIBLE);
                this.questionText.setText(R.string.rtb_question_highlow);
                break;
            case Inside_Outside:
                this.cardView2.setVisibility(View.VISIBLE);
                this.cardView2.setImageResource(this.card2.getImageId());
                this.outsideButton.setVisibility(View.VISIBLE);
                this.insideButton.setVisibility(View.VISIBLE);
                this.higherButton.setVisibility(View.GONE);
                this.lowerButton.setVisibility(View.GONE);
                this.questionText.setText(R.string.rtb_question_inout);
                break;
            case Suit:
                this.cardView3.setVisibility(View.VISIBLE);
                this.cardView3.setImageResource(this.card3.getImageId());
                this.outsideButton.setVisibility(View.GONE);
                this.insideButton.setVisibility(View.GONE);
                this.suitsButtons.setVisibility(View.VISIBLE);
                this.questionText.setText(R.string.rtb_question_suit);
                break;
            case Won:
                this.cardView4.setVisibility(View.VISIBLE);
                this.cardView4.setImageResource(this.card4.getImageId());
                this.questionText.setText(R.string.text_youwon);
                this.suitsButtons.setVisibility(View.GONE);
                this.restartButton.setVisibility(View.VISIBLE);
        }
        boolean aceIsLow = this.getSharedPreferences(getString(R.string.text_package),
                Context.MODE_PRIVATE).getBoolean(getString(R.string.s_acesalwayslow), true);
        this.warningAce.setText((aceIsLow) ? getString(R.string.rtb_warning_aceislow) :
                getString(R.string.rtb_warning_aceishigh));
        if (this.card1 != null && this.card1.getNumericValue(true) == 1)
            this.warningAce.setVisibility(View.VISIBLE);
        else if (this.card2 != null && this.card2.getNumericValue(true) == 1)
            this.warningAce.setVisibility(View.VISIBLE);
        else if (this.card3 != null && this.card3.getNumericValue(true) == 1)
            this.warningAce.setVisibility(View.VISIBLE);
        else if (this.card4 != null && this.card4.getNumericValue(true) == 1)
            this.warningAce.setVisibility(View.VISIBLE);
        else this.warningAce.setVisibility(View.INVISIBLE);
    }

    /**
     * Button press handler for the Red Button
     *
     * @param view the Red Button
     */
    public void guessRed(View view) {
        this.card1 = this.deck.drawRandom();
        Drawable d = card1.getDrawable(getApplicationContext(),
                getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                        .getInt(getString(R.string.s_general_cardskin), 1));
        Card.Suit s = this.card1.getSuit();
        if (s == Card.Suit.HEART || s == Card.Suit.DIAMOND)
            cv.flipAndMoveOn(d, this, State.High_Low);
        else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_ridethebus_carddrawn, (ViewGroup) findViewById(R.id.popup_ridethebus_carddrawn_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card1.toString(), "(You guessed Red)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            imageView.setImageDrawable(this.card1.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
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
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                }
            });
            cv.flipAndShowDialog(dialog, d);
        }
    }

    /**
     * The Button Press Handler for the Black Button
     *
     * @param view Black Button
     */
    public void guessBlack(View view) {
        this.card1 = this.deck.drawRandom();
        Drawable d = card1.getDrawable(getApplicationContext(),
                getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                        .getInt(getString(R.string.s_general_cardskin), 1));
        Card.Suit s = this.card1.getSuit();
        if (s == Card.Suit.HEART || s == Card.Suit.DIAMOND) {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_ridethebus_carddrawn, (ViewGroup) findViewById(R.id.popup_ridethebus_carddrawn_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card1.toString(), "(You guessed Black)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            imageView.setImageDrawable(this.card1.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
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
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                }
            });
            cv.flipAndShowDialog(dialog, d);
        } else
            cv.flipAndMoveOn(d, this, State.High_Low);
    }

    /**
     * The Higher Press Handler for the Higher Button
     *
     * @param view Higher Button
     */
    public void guessHigher(View view) {
        this.card2 = this.deck.drawRandom();
        boolean acesLow = this.getSharedPreferences(getString(R.string.text_package),
                Context.MODE_PRIVATE).getBoolean(getString(R.string.s_acesalwayslow), true);
        if (this.card2.getNumericValue(acesLow) > this.card1.getNumericValue(acesLow)) {
            this.setState(State.Inside_Outside);
        } else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_ridethebus_carddrawn,
                    (ViewGroup) findViewById(R.id.popup_ridethebus_carddrawn_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card2.toString(), "(You guessed Higher)"));
            if (this.warningAce.getVisibility() == View.VISIBLE || this.card2.getNumericValue(true) == 1)
                ((TextView) layout.findViewById(R.id.rtb_popup_warning_ace))
                        .setText((this.getSharedPreferences(getString(R.string.text_package),
                                Context.MODE_PRIVATE).getBoolean(getString(R.string.s_acesalwayslow), true)) ?
                                getString(R.string.rtb_warning_aceislow) : getString(R.string.rtb_warning_aceishigh));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            ((ImageView) layout.findViewById(R.id.rtb_popup_card1)).setImageDrawable(this.card1.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
            imageView.setImageDrawable(this.card2.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
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
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                }
            });
            dialog.show();
        }
    }

    /**
     * The Lower Press Handler for the Lower Button
     *
     * @param view Lower Button
     */
    public void guessLower(View view) {
        this.card2 = this.deck.drawRandom();
        boolean acesLow = this.getSharedPreferences(getString(R.string.text_package),
                Context.MODE_PRIVATE).getBoolean(getString(R.string.s_acesalwayslow), true);
        if (this.card2.getNumericValue(acesLow) < this.card1.getNumericValue(acesLow)) {
            this.setState(State.Inside_Outside);
        } else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_ridethebus_carddrawn,
                    (ViewGroup) findViewById(R.id.popup_ridethebus_carddrawn_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card2.toString(), "(You guessed Lower)"));
            if (this.warningAce.getVisibility() == View.VISIBLE || this.card2.getNumericValue(true) == 1)
                ((TextView) layout.findViewById(R.id.rtb_popup_warning_ace))
                        .setText((this.getSharedPreferences(getString(R.string.text_package),
                                Context.MODE_PRIVATE).getBoolean(getString(R.string.s_acesalwayslow), true)) ?
                                getString(R.string.rtb_warning_aceislow) : getString(R.string.rtb_warning_aceishigh));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            ((ImageView) layout.findViewById(R.id.rtb_popup_card1)).setImageDrawable(
                    this.card1.getDrawable(getApplicationContext(),
                            getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                                    .getInt(getString(R.string.s_general_cardskin), 1)));
            imageView.setImageDrawable(this.card2.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
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
                            .setTextColor(ContextCompat.getColor(getApplicationContext(),
                                    R.color.darkRed));
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                }
            });
            dialog.show();
        }
    }

    /**
     * The Outeside Press Handler for the Outside Button
     *
     * @param view Outside Button
     */
    public void guessOutside(View view) {
        this.card3 = this.deck.drawRandom();
        boolean acesLow = this.getSharedPreferences(getString(R.string.text_package),
                Context.MODE_PRIVATE).getBoolean(getString(R.string.s_acesalwayslow), true);
        int i1 = this.card1.getNumericValue(acesLow), i2 = this.card2.getNumericValue(acesLow),
                i3 = this.card3.getNumericValue(acesLow);
        if (i3 > Math.max(i1, i2) || i3 < Math.min(i1, i2)) {
            this.setState(State.Suit);
        } else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_ridethebus_carddrawn,
                    (ViewGroup) findViewById(R.id.popup_ridethebus_carddrawn_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card3.toString(), "(You guessed Outside)"));
            if (this.warningAce.getVisibility() == View.VISIBLE || this.card3.getNumericValue(true) == 1)
                ((TextView) layout.findViewById(R.id.rtb_popup_warning_ace))
                        .setText((this.getSharedPreferences(getString(R.string.text_package),
                                Context.MODE_PRIVATE).getBoolean(getString(R.string.s_acesalwayslow), true)) ?
                                getString(R.string.rtb_warning_aceislow) : getString(R.string.rtb_warning_aceishigh));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            ((ImageView) layout.findViewById(R.id.rtb_popup_card1))
                    .setImageDrawable(this.card1.getDrawable(getApplicationContext(),
                            getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                                    .getInt(getString(R.string.s_general_cardskin), 1)));
            ((ImageView) layout.findViewById(R.id.rtb_popup_card2))
                    .setImageDrawable(this.card2.getDrawable(getApplicationContext(),
                            getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                                    .getInt(getString(R.string.s_general_cardskin), 1)));
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
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                }
            });
            dialog.show();
        }
    }

    /**
     * The Inside Press Handler for the Inside Button
     *
     * @param view Inside Button
     */
    public void guessInside(View view) {
        this.card3 = this.deck.drawRandom();
        boolean acesLow = this.getSharedPreferences(getString(R.string.text_package),
                Context.MODE_PRIVATE).getBoolean(getString(R.string.s_acesalwayslow), true);
        int i1 = this.card1.getNumericValue(acesLow), i2 = this.card2.getNumericValue(acesLow),
                i3 = this.card3.getNumericValue(acesLow);
        if (i3 < Math.max(i1, i2) && i3 > Math.min(i1, i2)) {
            this.setState(State.Suit);
        } else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_ridethebus_carddrawn, (ViewGroup) findViewById(R.id.popup_ridethebus_carddrawn_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card3.toString(), "(You guessed Inside)"));
            if (this.warningAce.getVisibility() == View.VISIBLE || this.card3.getNumericValue(true) == 1)
                ((TextView) layout.findViewById(R.id.rtb_popup_warning_ace))
                        .setText((this.getSharedPreferences(getString(R.string.text_package),
                                Context.MODE_PRIVATE).getBoolean(getString(R.string.s_acesalwayslow), true)) ?
                                getString(R.string.rtb_warning_aceislow) : getString(R.string.rtb_warning_aceishigh));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            ((ImageView) layout.findViewById(R.id.rtb_popup_card1))
                    .setImageDrawable(this.card1.getDrawable(getApplicationContext(),
                            getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                                    .getInt(getString(R.string.s_general_cardskin), 1)));
            ((ImageView) layout.findViewById(R.id.rtb_popup_card2)).setImageDrawable(
                    this.card2.getDrawable(getApplicationContext(), getSharedPreferences(
                            getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
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
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                }
            });
            dialog.show();
        }
    }

    /**
     * The Club Press Handler for the Club Button
     *
     * @param view Club Button
     */
    public void guessClub(View view) {
        this.card4 = this.deck.drawRandom();
        Card.Suit s = this.card4.getSuit();
        if (s == Card.Suit.CLUB) this.setState(State.Won);
        else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_ridethebus_carddrawn, (ViewGroup) findViewById(R.id.popup_ridethebus_carddrawn_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card4.toString(), "(You guessed Clubs)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            ((ImageView) layout.findViewById(R.id.rtb_popup_card1)).setImageDrawable(this.card1.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
            ((ImageView) layout.findViewById(R.id.rtb_popup_card2)).setImageDrawable(this.card2.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
            ((ImageView) layout.findViewById(R.id.rtb_popup_card3)).setImageDrawable(this.card3.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
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
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                }
            });
            dialog.show();
        }
    }

    /**
     * The Heart Press Handler for the Heart Button
     *
     * @param view Heart Button
     */
    public void guessHeart(View view) {
        this.card4 = this.deck.drawRandom();
        Card.Suit s = this.card4.getSuit();
        if (s == Card.Suit.HEART) this.setState(State.Won);
        else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_ridethebus_carddrawn, (ViewGroup) findViewById(R.id.popup_ridethebus_carddrawn_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card4.toString(), "(You guessed Hearts)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            ((ImageView) layout.findViewById(R.id.rtb_popup_card1)).setImageDrawable(this.card1.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
            ((ImageView) layout.findViewById(R.id.rtb_popup_card2)).setImageDrawable(this.card2.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
            ((ImageView) layout.findViewById(R.id.rtb_popup_card3)).setImageDrawable(this.card3.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
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
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                }
            });
            dialog.show();
        }
    }

    /**
     * The Diamond Press Handler for the Diamond Button
     *
     * @param view Diamond Button
     */
    public void guessDiamond(View view) {
        this.card4 = this.deck.drawRandom();
        Card.Suit s = this.card4.getSuit();
        if (s == Card.Suit.DIAMOND) this.setState(State.Won);
        else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_ridethebus_carddrawn, (ViewGroup) findViewById(R.id.popup_ridethebus_carddrawn_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card4.toString(), "(You guessed Diamonds)"));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            ((ImageView) layout.findViewById(R.id.rtb_popup_card1)).setImageDrawable(this.card1.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
            ((ImageView) layout.findViewById(R.id.rtb_popup_card2)).setImageDrawable(this.card2.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
            ((ImageView) layout.findViewById(R.id.rtb_popup_card3)).setImageDrawable(this.card3.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
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
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                }
            });
            dialog.show();
        }
    }

    /**
     * The Spade Press Handler for the Spade Button
     *
     * @param view Spade Button
     */
    public void guessSpade(View view) {
        this.card4 = this.deck.drawRandom();
        Card.Suit s = this.card4.getSuit();
        if (s == Card.Suit.SPADE) this.setState(State.Won);
        else {
            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_ridethebus_carddrawn, (ViewGroup) findViewById(R.id.popup_ridethebus_carddrawn_root));
            ((TextView) layout.findViewById(R.id.rtb_popup_text)).setText(String.format("%s%s\n%s",
                    getString(R.string.rtb_incorrectmessage), this.card4.toString(), "(You guessed Spades)"));
            if (this.warningAce.getVisibility() == View.VISIBLE || this.card4.getNumericValue(true) == 1)
                ((TextView) layout.findViewById(R.id.rtb_popup_warning_ace)).setText((this.getSharedPreferences(getString(R.string.text_package),
                        Context.MODE_PRIVATE).getBoolean(getString(R.string.s_acesalwayslow), true)) ?
                        getString(R.string.rtb_warning_aceislow) : getString(R.string.rtb_warning_aceishigh));
            ImageView imageView = (ImageView) layout.findViewById(R.id.rtb_popup_maincard);
            ((ImageView) layout.findViewById(R.id.rtb_popup_card1)).setImageDrawable(this.card1.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
            ((ImageView) layout.findViewById(R.id.rtb_popup_card2)).setImageDrawable(this.card2.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
            ((ImageView) layout.findViewById(R.id.rtb_popup_card3)).setImageDrawable(this.card3.getDrawable(getApplicationContext(),
                    getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                            .getInt(getString(R.string.s_general_cardskin), 1)));
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
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                }
            });
            dialog.show();
        }
    }

    /**
     * Restart the Game, reshuffle the deck
     *
     * @param view Irrelevant
     */
    public void restart(View view) {
        this.deck.reset();
        this.setState(State.Color);
        Toast.makeText(getApplicationContext(), getString(R.string.text_resetallcards), Toast.LENGTH_LONG).show();
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

    /**
     * Options Item clicked
     *
     * @param item the item clicked
     * @return like, always true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.circlemain_resetcards:
                this.restart(null);
                return true;
            case R.id.rtb_settings:
                try {
                    showSettingsPopup();
                } catch (Exception e) {
                    DrinkingGamesGlobal.logException(e);
                }
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
        layout.findViewById(R.id.settings_tabs_ridethebus).callOnClick();
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

    /**
     * Used by the FSM to determine where in the game you are
     */
    public enum State {
        Color, High_Low, Inside_Outside, Suit, Won
    }
}
