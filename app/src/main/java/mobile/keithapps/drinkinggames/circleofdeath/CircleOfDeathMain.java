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
import android.widget.CheckBox;
import android.widget.EditText;
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
        /** For now, just use toast
         if (Build.VERSION.SDK_INT >= 21)
         android.support.design.widget.Snackbar.make(parentlayout, "This is main activity", Snackbar.LENGTH_LONG)
         .setAction("CLOSE", new View.OnClickListener() {
        @Override public void onClick(View view) {

        }
        })
         .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
         .show();
         */
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

    private void showSettingsPopup() {
        final AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.popup_settings,
                (ViewGroup) findViewById(R.id.settingsscreen_scrollview_root));
        SharedPreferences prefs = getSharedPreferences(getString(R.string.text_package), Context.MODE_PRIVATE);
        boolean acesLow = prefs.getBoolean(getString(R.string.setting_acesalwayslow), true);
        ((CheckBox) layout.findViewById(R.id.settingsscreen_bizkit_insult)).setChecked(
                prefs.getBoolean(getString(R.string.settings_bizkit_insultbizkit), true));
        ((CheckBox) layout.findViewById(R.id.settingsscreen_acesarelow)).setChecked(acesLow);
        //Put Current Values in
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ace_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_ace_actionname_key),
                        getString(R.string.circleofdeath_carddirection_ace)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ace_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_ace_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_ace)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_two_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_two_actionname_key),
                        getString(R.string.circleofdeath_carddirection_two)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_two_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_two_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_two)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_three_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_three_actionname_key),
                        getString(R.string.circleofdeath_carddirection_three)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_three_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_three_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_three)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_four_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_four_actionname_key),
                        getString(R.string.circleofdeath_carddirection_four)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_four_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_four_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_four)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_five_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_five_actionname_key),
                        getString(R.string.circleofdeath_carddirection_five)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_five_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_five_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_five)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_six_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_six_actionname_key),
                        getString(R.string.circleofdeath_carddirection_six)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_six_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_six_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_six)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_seven_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_seven_actionname_key),
                        getString(R.string.circleofdeath_carddirection_seven)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_seven_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_seven_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_seven)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_eight_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_eight_actionname_key),
                        getString(R.string.circleofdeath_carddirection_eight)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_eight_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_eight_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_eight)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_nine_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_nine_actionname_key),
                        getString(R.string.circleofdeath_carddirection_nine)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_nine_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_nine_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_nine)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ten_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_ten_actionname_key),
                        getString(R.string.circleofdeath_carddirection_ten)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ten_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_ten_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_ten)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_jack_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_jack_actionname_key),
                        getString(R.string.circleofdeath_carddirection_jack)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_jack_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_jack_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_jack)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_queen_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_queen_actionname_key),
                        getString(R.string.circleofdeath_carddirection_queen)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_queen_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_queen_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_queen)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_king_actionname))
                .setText(prefs.getString(getString(R.string.settings_cod_king_actionname_key),
                        getString(R.string.circleofdeath_carddirection_king)));
        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_king_actiontext))
                .setText(prefs.getString(getString(R.string.settings_cod_king_actiontext_key),
                        getString(R.string.circleofdeath_carddirection_description_king)));
        try {
            ((TextView) layout.findViewById(R.id.settingsscreen_textview_emaildeveloper))
                    .setText(String.format("     //Created by Keith MacKay\n\n     //Feedback: keith.mackay3@gmail.com\n\n     //Version: %s",
                            getPackageManager().getPackageInfo(getPackageName(), 0).versionName));
        } catch (Exception e) {
            DrinkingGamesGlobal.logException(e);
        }
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
                prefsEditor.putBoolean(getString(R.string.setting_acesalwayslow),
                        ((CheckBox) layout.findViewById(R.id.settingsscreen_acesarelow)).isChecked());
                prefsEditor.putString(getString(R.string.settings_cod_ace_actionname_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ace_actionname))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_ace_actiontext_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ace_actiontext))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_two_actionname_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_two_actionname))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_two_actiontext_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_two_actiontext))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_three_actionname_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_three_actionname))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_three_actiontext_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_three_actiontext))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_four_actionname_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_four_actionname))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_four_actiontext_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_four_actiontext))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_five_actionname_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_five_actionname))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_five_actiontext_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_five_actiontext))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_six_actionname_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_six_actionname))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_six_actiontext_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_six_actiontext))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_seven_actionname_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_seven_actionname))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_seven_actiontext_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_seven_actiontext))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_eight_actionname_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_eight_actionname))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_eight_actiontext_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_eight_actiontext))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_nine_actionname_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_nine_actionname))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_nine_actiontext_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_nine_actiontext))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_ten_actionname_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ten_actionname))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_ten_actiontext_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_ten_actiontext))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_jack_actionname_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_jack_actionname))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_jack_actiontext_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_jack_actiontext))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_queen_actionname_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_queen_actionname))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_queen_actiontext_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_queen_actiontext))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_king_actionname_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_king_actionname))
                                .getText().toString());
                prefsEditor.putString(getString(R.string.settings_cod_king_actiontext_key),
                        ((EditText) layout.findViewById(R.id.settingsscreen_circleofdeath_king_actiontext))
                                .getText().toString());
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
