package mobile.keithapps.drinkinggames.circleofdeath;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mobile.keithapps.CardsAndDecks.Card;
import mobile.keithapps.CardsAndDecks.CardDeck;
import mobile.keithapps.customlayouts.CircleLayout;
import mobile.keithapps.drinkinggames.DrinkingGamesGlobal;
import mobile.keithapps.drinkinggames.R;

/**
 * Controller for the Circle of Death Screen
 */
public class CircleOfDeathMain extends AppCompatActivity {
    /**
     * Whether or not the circle has been broken yet
     */
    private boolean circleBroken;
    /**
     * The CardDeck manager
     */
    private CardDeck cod;

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
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21)
            this.setTheme(R.style.Theme_FullscreenTheme_MaterialDark);

        this.circleBroken = false;
        this.cod = new CardDeck();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        double increment = 6.92307692308;
        CircleLayout cl = (CircleLayout) findViewById(R.id.circleofdeath_circlelayout);
        for (int i = 0; i < 52; i++) {
            ImageView iv = (ImageView) cl.getChildAt(i);
            iv.setRotation((float) ((increment * i) + .5));
            iv.setImageResource(R.drawable.cardback);
        }
    }

    /**
     * Check for a break in the cards
     */
    private void checkForBreak() {
        if (circleBroken) return;
        CircleLayout cl = (CircleLayout) findViewById(R.id.circleofdeath_circlelayout);
        int counter = 0;
        for (int i = 0; i < 52; i++) {
            if (cl.getChildAt(i).getVisibility() == View.GONE) {
                counter++;
                if (counter >= 7) {
                    this.circleBroken = true;
                    AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                    final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.popup_circleofdeath_imageandtext,
                            (ViewGroup) findViewById(R.id.popup_ridethebus_carddrawn_root));
                    TextView textView = (TextView) layout.findViewById(R.id.text_on_popup);
                    textView.setText(R.string.circleofdeath_circlebrokenmessage);
                    ImageView imageView = (ImageView) layout.findViewById(R.id.image_on_popup);
                    imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.brokencircle));
                    final TextView instructionsView = (TextView) layout.findViewById(R.id.directions_on_popup);
                    instructionsView.setText(R.string.circleofdeath_circlebrokenmessage_2);
                    imageDialog.setView(layout);
                    imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
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
                    return;
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
     * Make all of the cards visible
     */
    private void resetCards() {
        CircleLayout cl = (CircleLayout) findViewById(R.id.circleofdeath_circlelayout);
        for (int i = 0; i < 52; i++)
            cl.getChildAt(i).setVisibility(View.VISIBLE);
        this.circleBroken = false; //Circle no longer broken
        this.cod.reset(); //Restore all cards to the deck

        //Inform the user. Toast for builds that do not support Snackbar
        Toast.makeText(getApplicationContext(), "Reset All Cards", Toast.LENGTH_SHORT).show();
    }

    /**
     * Whenever an option item is selected
     *
     * @param item the item that was pressed
     * @return like, always true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.circlemain_resetcards:
                this.resetCards();
                return true;
            case R.id.circlemain_settings:
                try {
                    showSettingsPopup();
                } catch (Exception e) {
                    DrinkingGamesGlobal.logException(e);
                }
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
        Card drawn = this.cod.drawRandom();
        showDrawn(drawn);

        //Check for a break in the circle
        this.checkForBreak();
    }

    /**
     * Show the card that the player drew to them in an alertdialog
     *
     * @param card the card that was drawn
     */
    private void showDrawn(final Card card) {
        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup_circleofdeath_imageandtext,
                (ViewGroup) findViewById(R.id.popup_ridethebus_carddrawn_root));
        TextView textView = (TextView) layout.findViewById(R.id.text_on_popup);
        textView.setText(card.toString());
        ImageView imageView = (ImageView) layout.findViewById(R.id.image_on_popup);
        imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), card.getImageId()));
        ((TextView) layout.findViewById(R.id.directions_on_popup))
                .setText(this.getSharedPreferences(getString(R.string.text_package),
                        Context.MODE_PRIVATE).getString(getString(card.getActionNameKey()),
                        getString(card.getDefaultDirections())));
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
        final AlertDialog dialog = imageDialog.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                        .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
                dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
            }
        });
        dialog.show();
    }

    /**
     * Show the action description of the drawn card
     *
     * @param card the card that was drawn
     */
    public void showDescription(final Card card) {
        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup_circleofdeath_imageandtext,
                (ViewGroup) findViewById(R.id.popup_ridethebus_carddrawn_root));
        TextView textView = (TextView) layout.findViewById(R.id.text_on_popup);
        textView.setText(card.getFaceValue().toString());
        ImageView imageView = (ImageView) layout.findViewById(R.id.image_on_popup);
        imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), card.getImageId()));
        ((TextView) layout.findViewById(R.id.directions_on_popup))
                .setText(this.getSharedPreferences(getString(R.string.text_package),
                        Context.MODE_PRIVATE).getString(getString(card.getActionDescriptionKey()),
                        getString(card.getDefaultActionDescription())));
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
                dialogInterface.dismiss();
                try {
                    showSettingsPopup();
                } catch (Exception e) {
                    DrinkingGamesGlobal.logException(e);
                }
            }
        });
        final AlertDialog dialog = imageDialog.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                        .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
                dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkRed));
            }
        });
        dialog.show();
    }

    /**
     * Show the settings popup
     */
    private void showSettingsPopup() {
        final AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        SharedPreferences prefs = getSharedPreferences(getString(R.string.text_package), Context.MODE_PRIVATE);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View l = inflater.inflate(R.layout.popup_settings,
                (ViewGroup) findViewById(R.id.settingsscreen_scrollview_root));
        final View layout = DrinkingGamesGlobal.loadSettingsToLayout(l, prefs, this);
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
            }
        });
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        CircleLayout cl = (CircleLayout) findViewById(R.id.circleofdeath_circlelayout);
        for (int i = 0; i < 52; i++) {
            ImageView b = (ImageView) cl.getChildAt(i);
            Drawable drawable = b.getDrawable();
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                bitmap.recycle();
            }
        }
        super.onDestroy();
    }
}
