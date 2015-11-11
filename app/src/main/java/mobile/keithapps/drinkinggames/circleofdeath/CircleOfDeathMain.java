package mobile.keithapps.drinkinggames.circleofdeath;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;

import mobile.keithapps.CardsAndDecks.Card;
import mobile.keithapps.customlayouts.CircleLayout;
import mobile.keithapps.drinkinggames.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CircleOfDeathMain extends AppCompatActivity {

    private boolean circleBroken;
    private CircleOfDeathController cod;

    /**
     * Initialize the screen and rotate all of the cards to make them look
     * pretty
     *
     * @param savedInstanceState Passed by the system from the last screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_circleofdeathmain);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Rotate all cards for aesthetics
        double increment = 6.92307692308;
        CircleLayout cl = (CircleLayout) findViewById(R.id.circleofdeath_circlelayout);
        for (int i = 0; i < 52; i++) {
            Button b = (Button) cl.getChildAt(i);
            b.setRotation((float) ((increment * i) + .5));
        }
        this.circleBroken = false;
        this.cod = new CircleOfDeathController();
    }

    private void checkForBreak() {
        if (circleBroken) return;
        CircleLayout cl = (CircleLayout) findViewById(R.id.circleofdeath_circlelayout);
        int counter = 0;
        for (int i = 0; i < 52; i++) {
            if (cl.getChildAt(i).getVisibility() == View.GONE) {
                counter++;
                if (counter == 7) {
                    this.circleBroken = true;
                        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                        View layout = inflater.inflate(R.layout.image_and_text_popup,
                                (ViewGroup) findViewById(R.id.layout_root));
                        TextView textView = (TextView) layout.findViewById(R.id.text_on_popup);
                        textView.setText(R.string.circle_broken_message);
                        ImageView imageView = (ImageView) layout.findViewById(R.id.image_on_popup);
                        imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.brokencircle));
                        final TextView instructionsView = (TextView) layout.findViewById(R.id.directions_on_popup);
                        instructionsView.setText(R.string.circle_broken_message_2);
                        imageDialog.setView(layout);
                        imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = imageDialog.create();
                        dialog.show();
                }
            } else counter = 0;
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
        inflater.inflate(R.menu.circlemain_menu, menu);
        return true;
    }

    /**
     * Make all of the cards visibles
     */
    private void resetCards() {
        CircleLayout cl = (CircleLayout) findViewById(R.id.circleofdeath_circlelayout);
        for (int i = 0; i < 52; i++) {
            cl.getChildAt(i).setVisibility(View.VISIBLE);
        }
        this.circleBroken = false; //Circle no longer broken
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.circlemain_resetcards:
                this.resetCards();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * Make the card that was clicked invisible
     * Also, show popup with information of that card
     *
     * @param view the button that was clicked
     */
    public void drawCard(View view) {
        //Make Button Invisible
        view.setVisibility(View.GONE);

        //Show drawn card
        Card drawn = this.cod.drawCard();
        showDrawn(drawn);

        //Check for a break in the circle
        this.checkForBreak();
    }

    private void showDrawn(final Card card) {
        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.image_and_text_popup,
                (ViewGroup) findViewById(R.id.layout_root));
        TextView textView = (TextView) layout.findViewById(R.id.text_on_popup);
        textView.setText(card.toString());
        ImageView imageView = (ImageView) layout.findViewById(R.id.image_on_popup);
        imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), card.getImageId()));
        final TextView instructionsView = (TextView) layout.findViewById(R.id.directions_on_popup);
        instructionsView.setText(getResources().getString(card.getDirections()));
        imageDialog.setView(layout);
        imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        imageDialog.setNegativeButton("Description", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
                showDescription(card);
            }
        });
        AlertDialog dialog = imageDialog.create();
        //dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.WHITE);
        //dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.WHITE);
        dialog.show();
    }

    public void showDescription(final Card card) {
        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.image_and_text_popup,
                (ViewGroup) findViewById(R.id.layout_root));
        TextView textView = (TextView) layout.findViewById(R.id.text_on_popup);
        textView.setText(card.getFaceValue().toString());
        ImageView imageView = (ImageView) layout.findViewById(R.id.image_on_popup);
        imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), card.getImageId()));
        final TextView instructionsView = (TextView) layout.findViewById(R.id.directions_on_popup);
        instructionsView.setText(getResources().getString(getActionDescription(card.getFaceValue())));
        imageDialog.setView(layout);
        imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        imageDialog.setNegativeButton("Edit Action", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //openSettings(card);
            }
        });
        AlertDialog dialog = imageDialog.create();
        //dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.WHITE);
        //dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.WHITE);
        dialog.show();
    }

    public int getActionDescription(Card.FaceValue card) {
        switch (card) {
            case ACE:
                return R.string.ace_directions_description;
            case TWO:
                return R.string.two_directions_description;
            case THREE:
                return R.string.three_directions_description;
            case FOUR:
                return R.string.four_directions_description;
            case FIVE:
                return R.string.five_directions_description;
            case SIX:
                return R.string.six_directions_description;
            case SEVEN:
                return R.string.seven_directions_description;
            case EIGHT:
                return R.string.eight_directions_description;
            case NINE:
                return R.string.nine_directions_description;
            case TEN:
                return R.string.ten_directions_description;
            case JACK:
                return R.string.jack_directions_description;
            case QUEEN:
                return R.string.queen_directions_description;
            case KING:
                return R.string.king_directions_description;
        }
        return -1;
    }
}