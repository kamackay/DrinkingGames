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

import java.util.Locale;

import mobile.keithapps.CardsAndDecks.Card;
import mobile.keithapps.CardsAndDecks.CardDeck;
import mobile.keithapps.customviews.CardView;
import mobile.keithapps.customviews.CircleLayout;
import mobile.keithapps.drinkinggames.DrinkingGamesGlobal;
import mobile.keithapps.drinkinggames.R;

/**
 * Controller for the Circle of Death Screen
 */
public class CircleOfDeathMain extends AppCompatActivity {
    /**
     * Use this as a bastardized semaphore to resolve the ability to
     * open multiple cards at the same time
     */
    private boolean lock;
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
        cod = new CardDeck();
        cod.setDeck(getApplicationContext());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        final SharedPreferences prefs = getSharedPreferences(
                getString(R.string.text_package), MODE_PRIVATE);
        lock = true;
        final double increment = 6.92307692308;
        final CircleLayout cl = (CircleLayout) findViewById(R.id.circleofdeath_circlelayout);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 52; i++) {
                    final int finalI = i;
                    final ImageView iv = (ImageView) cl.getChildAt(finalI);
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iv.setImageResource(R.drawable.cardback_nologo);
                                iv.setRotation((float) ((increment * finalI) + .5));
                                if (prefs.getBoolean(String.format(Locale.getDefault(),
                                        "card.%d", finalI), true)) iv.setVisibility(View.VISIBLE);
                                else iv.setVisibility(View.GONE);
                            }
                        });
                    } catch (Exception e) {
                        //This shouldn't happen, like, ever
                    }
                }
                lock = false;
            }
        }).start();
    }

    /**
     * Check for a break in the cards
     */
    private void checkForBreak() {
        if (isBroken()) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                CircleLayout cl = (CircleLayout) findViewById(R.id.circleofdeath_circlelayout);
                int counter = 0;
                for (int i = 0; i < 52; i++) {
                    if (cl.getChildAt(i).getVisibility() == View.GONE) {
                        counter++;
                        if (counter >= 7) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    broken(true);
                                    AlertDialog.Builder imageDialog = new AlertDialog.Builder(CircleOfDeathMain.this);
                                    final LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View layout = inflater.inflate(R.layout.popup_circleofdeath_imageandtext,
                                            (ViewGroup) findViewById(R.id.popup_ridethebus_carddrawn_root));
                                    TextView textView = (TextView) layout.findViewById(R.id.text_on_popup);
                                    textView.setText(R.string.cod_circlebrokenmessage);
                                    ImageView imageView = (ImageView) layout.findViewById(R.id.image_on_popup);
                                    imageView.setVisibility(View.GONE);
                                    final TextView instructionsView = (TextView) layout.findViewById(R.id.directions_on_popup);
                                    instructionsView.setText(R.string.cod_circlebrokenmessage_2);
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
                                }
                            });
                            return;
                        }
                    } else counter = 0;
                }
            }
        }).start();
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

    public static final String circleBroken = "circleBroken";

    /**
     * Make all of the cards visible
     */
    private void resetCards() {
        CircleLayout cl = (CircleLayout) findViewById(R.id.circleofdeath_circlelayout);
        for (int i = 0; i < 52; i++)
            cl.getChildAt(i).setVisibility(View.VISIBLE);
        broken(false);
        cod.setDeck(); //Restore all cards to the deck
        setAllShown();
        putAllBackInDeck();
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
        if (this.lock) return; //Statement that blocks multiple simeultaneous cards
        this.lock = true; //Claim this as the only drawCard to allow run (right now)
        //Make Button Invisible
        view.setVisibility(View.GONE);

        //Show drawn card
        Card drawn = cod.drawRandom();
        showDrawn(drawn);

        //Check for a break in the circle
        checkForBreak();
        try {
            setHidden(((ViewGroup) view.getParent()).indexOfChild(view));
        } catch (Exception e) {
            //It's OK
        }

        //this.lock = false;
        //Put this on the off chance that the popup is cancelled with the back button,
        //because that would not trigger either of the button presses
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
        final TextView textView = (TextView) layout.findViewById(R.id.text_on_popup);
        textView.setText(card.toString());
        final CardView imageView = (CardView) layout.findViewById(R.id.image_on_popup);
        final TextView directions = (TextView) layout.findViewById(R.id.directions_on_popup);
        directions.setText(this.getSharedPreferences(getString(R.string.text_package),
                Context.MODE_PRIVATE).getString(getString(card.getActionNameKey()),
                getString(card.getDefaultDirections())));
        imageDialog.setView(layout);
        imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                lock = false;
            }
        });
        imageDialog.setNegativeButton("Description", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
                showDescription(card);
                lock = false;
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
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setAllCaps(false);
                directions.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                if (getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                        .getBoolean(getString(R.string.s_general_showanimations), true)) {
                    imageView.flipThen(card.getDrawable(getApplicationContext()), new Runnable() {
                        @Override
                        public void run() {
                            textView.setVisibility(View.VISIBLE);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(500);
                                        CircleOfDeathMain.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                directions.setVisibility(View.VISIBLE);
                                            }
                                        });
                                    } catch (Exception e) {
                                        DrinkingGamesGlobal.logException(e);
                                    }
                                }
                            }).start();
                        }
                    });
                } else {
                    textView.setVisibility(View.VISIBLE);
                    directions.setVisibility(View.VISIBLE);
                    imageView.setImageDrawable(card.getDrawable(getApplicationContext()));
                }
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                lock = false;
            }
        });
        dialog.show();
        removeFromDeck(card);
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
        imageView.setImageDrawable(card.getDrawable(getApplicationContext()));
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
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setAllCaps(false);
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
                (ViewGroup) findViewById(R.id.settings_scrollview_root));
        final View layout = DrinkingGamesGlobal.loadSettingsToLayout(l, prefs, this);
        layout.findViewById(R.id.settings_tabs_circleofdeath).callOnClick();
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

    void setHidden(int i) {
        SharedPreferences.Editor edit = getSharedPreferences(getString(R.string.text_package),
                MODE_PRIVATE).edit();
        edit.putBoolean(String.format(Locale.getDefault(), "card.%d", i), false);
        edit.apply();
    }

    void setAllShown() {
        SharedPreferences.Editor edit = getSharedPreferences(getString(R.string.text_package),
                MODE_PRIVATE).edit();
        for (int i = 0; i < 52; i++)
            edit.putBoolean(String.format(Locale.getDefault(), "card.%d", i), true);
        edit.apply();
    }

    void putAllBackInDeck(){
        SharedPreferences.Editor edit = getSharedPreferences(
                getString(R.string.text_package), Context.MODE_PRIVATE).edit();
        for (Card.FaceValue face : Card.FaceValue.values())
            for (Card.Suit suit : Card.Suit.values())
                edit.putBoolean(String.format(Locale.getDefault(), "deck.%s_%s",
                        face.toString(), suit.toString()), true);
        edit.apply();
    }

    void removeFromDeck(final Card card) {
        SharedPreferences.Editor edit = getSharedPreferences(getString(R.string.text_package),
                MODE_PRIVATE).edit();
        edit.putBoolean(String.format(Locale.getDefault(), "deck.%s_%s",
                card.getFaceValue().toString(), card.getSuit().toString()), false);
        edit.apply();
    }

    void broken(boolean broken) {
        SharedPreferences.Editor edit = getSharedPreferences(getString(R.string.text_package),
                MODE_PRIVATE)                .edit();
        edit.putBoolean(circleBroken, broken);
        edit.apply();
    }

    boolean isBroken(){
        return getSharedPreferences(getString(R.string.text_package), MODE_PRIVATE)
                .getBoolean(circleBroken, false);
    }
}