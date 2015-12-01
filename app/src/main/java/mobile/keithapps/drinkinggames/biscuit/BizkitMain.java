package mobile.keithapps.drinkinggames.biscuit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import mobile.keithapps.drinkinggames.DrinkingGamesGlobal;
import mobile.keithapps.drinkinggames.R;

/**
 * Created by Keith on 11/19/2015.
 */
public class BizkitMain extends AppCompatActivity {
    private TextView whatToDoTextView;
    private ImageView leftDieView, rightDieView;
    private String bizkitName;
    private State currState;
    private TextView currentBizkitView;
    private boolean bizkitIsMale;

    /**
     * Initialize the screen and rotate all of the cards to make them look
     * pretty
     *
     * @param savedInstanceState Passed by the system from the last screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizkit_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) this.setTheme(R.style.Theme_FullscreenTheme_MaterialDark);
        this.bizkitName = "";

        //Get Refrences to objects
        this.whatToDoTextView = (TextView) findViewById(R.id.bizkit_whattodotext);
        this.rightDieView = (ImageView) findViewById(R.id.bizkit_rightdie_imageview);
        this.leftDieView = (ImageView) findViewById(R.id.bizkit_leftdie_imageview);
        this.currentBizkitView = (TextView) findViewById(R.id.bizkit_currentbizkit_text);

        //Start Actually doing things
        this.setState(State.FindBiscuit);
    }

    private void setState(State state) {
        boolean change = !(state == this.currState);
        this.currState = state;
        switch (state) {
            case FindBiscuit:
                this.bizkitName = "";
                this.whatToDoTextView.setText(getString(R.string.bizkit_welcometext));
                this.currentBizkitView.setText(R.string.bizkit_message_nocurrentbizkit);
                break;
            case DispenseDrinks:
                if (change)
                    this.whatToDoTextView.setText(getString(R.string.bizkit_message_message_dispensedrink));
                if (this.getSharedPreferences(getString(R.string.text_package), Context.MODE_PRIVATE)
                        .getBoolean(getString(R.string.settings_bizkit_insultbizkit), true))
                    this.currentBizkitView.setText(String.format("Current Bizkit: %s (%s)",
                            this.bizkitName, DrinkingGamesGlobal.getRandomInsult(getResources())));
                else
                    this.currentBizkitView.setText(String.format("Current Bizkit: %s", this.bizkitName));
                break;
        }
    }

    private String getBizkitPronoun() {
        return (this.bizkitIsMale) ? "He" : "She";
    }

    public void rollDie(View view) {
        Random rand = new Random();
        final int rightDie = rand.nextInt(6) + 1;
        final int leftDie = rand.nextInt(6) + 1;
        this.rightDieView.setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
        this.leftDieView.setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
        if (this.currState == State.FindBiscuit) {
            if ((leftDie + rightDie) == 7) {
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                final EditText editText = (EditText) layout.findViewById(R.id.popup_bizkit_youarebizkit_edittext);
                final RadioButton isMale = (RadioButton) layout.findViewById(R.id.popup_bizkit_youarebizkit_combobox_male);
                layout.findViewById(R.id.popup_bizkit_youarebizkit_edittext_container)
                        .setVisibility(View.VISIBLE);
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
                imageDialog.setView(layout);
                imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bizkitName = editText.getText().toString().trim();
                        bizkitIsMale = isMale.isChecked();
                        dialog.dismiss();
                        if (getSharedPreferences(getString(R.string.text_package), Context.MODE_PRIVATE)
                                .getBoolean(getString(R.string.settings_bizkit_insultbizkit), true))
                            Toast.makeText(getApplicationContext(), String.format("Christ, %s, get your shit together.",
                                    bizkitName), Toast.LENGTH_LONG).show();
                        setState(State.DispenseDrinks);
                    }
                });
                final AlertDialog dialog = imageDialog.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setTextColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.darkRed));
                    }
                });
                dialog.show();
            }
        } else {
            int sum = leftDie + rightDie;
            String message = "";
            if (leftDie == 3 || rightDie == 3) {
                String pronoun = this.getBizkitPronoun().toLowerCase();
                String temp = String.format("If %s rolled this, %s is no longer the Bizkit, and another Bizkit must be found. If %s did not roll this, then %s have to drink once per '3' present",
                        bizkitName, pronoun, bizkitName, pronoun);
                message += temp;
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                        String.format("%s\n\nWas %s the one that rolled this?", temp, bizkitName));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
                imageDialog.setView(layout);
                imageDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),
                                String.format("%s is no longer the Bizkit", bizkitName),
                                Toast.LENGTH_LONG).show();
                        bizkitName = "";
                        setState(State.FindBiscuit);
                    }
                });
                imageDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if ((leftDie + rightDie) == 6)
                            showPopupMessage(String.format("In that case, %s has to take 2 drinks",
                                    bizkitName));
                        else showPopupMessage(String.format("In that case, %s has to take 1 drink",
                                bizkitName));

                        setState(State.DispenseDrinks);
                    }
                });
                final AlertDialog dialog = imageDialog.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setTextColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.darkRed));
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                                .setTextColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.darkRed));
                    }
                });
                dialog.show();
            } else if (leftDie == rightDie) {
                if (leftDie == 1) {
                    message = "Two ones:\nEveryone takes a drink!";
                    AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                    final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                            (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                    ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext))
                            .setText(message);
                    ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                            .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                    ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                            .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
                    imageDialog.setView(layout);
                    imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            setState(State.DispenseDrinks);
                        }
                    });
                    final AlertDialog dialog = imageDialog.create();
                    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface arg0) {
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                    .setTextColor(ContextCompat.getColor(getApplicationContext(),
                                            R.color.darkRed));
                        }
                    });
                    dialog.show();
                } else if (leftDie == 6) {
                    //Roller has to invent a rule which will be applied for the rest of the game.
                    // The non respect of this rule will result to a drink.
                    message = String.format("%s%s%s", "Two sixes:\nRoller has to invent a rule which",
                            " will be applied for the rest of the game.\n\nThe non respect",
                            " of this rule will result to a drink.");
                    AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                    final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                            (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                    ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                            message);
                    ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                            .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                    ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                            .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
                    imageDialog.setView(layout);
                    imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            setState(State.DispenseDrinks);
                        }
                    });
                    final AlertDialog dialog = imageDialog.create();
                    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface arg0) {
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                    .setTextColor(ContextCompat.getColor(getApplicationContext(),
                                            R.color.darkRed));
                        }
                    });
                    dialog.show();
                } else {
                    //Roller gives drinks to one or several players. ex. a 5-5 would mean the roller
                    // hands out 5 drinks
                    message = String.format("Two %d's:\nRoller can assign %d drinks to other players.",
                            leftDie, sum);
                    AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                    final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                            (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                    ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                            message);
                    ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                            .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                    ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                            .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
                    imageDialog.setView(layout);
                    imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setState(State.DispenseDrinks);
                            dialog.dismiss();
                        }
                    });
                    final AlertDialog dialog = imageDialog.create();
                    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface arg0) {
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                    .setTextColor(ContextCompat.getColor(getApplicationContext(),
                                            R.color.darkRed));
                        }
                    });
                    dialog.show();
                }
            } else if (sum == 3) {
                message = String.format("The sum was 3\n%s", "Challenge someone else!\nThe game will then decide who won when you press 'Challenge Accepted'");
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                        message);
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
                imageDialog.setView(layout);
                imageDialog.setPositiveButton("Challenge Accepted", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Random r = new Random();
                        int roll1 = r.nextInt(6) + 1, roll2 = r.nextInt(6) + 1;
                        if (roll1 > roll2)
                            showPopupMessage(String.format("The person who rolled this won! The person who was challenged has to drink %d times", roll1 - roll2));
                        else if (roll1 != roll2)
                            showPopupMessage(String.format("The person who was challenged won! The person who rolled this has to drink %d times", roll2 - roll1));
                        else
                            showPopupMessage("The challenge was a draw! Both the person who rolled this and the challenger drink twice!");
                        dialog.dismiss();
                    }
                });
                final AlertDialog dialog = imageDialog.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setTextColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.darkRed));
                    }
                });
                dialog.show();
            } else if (sum == 7) {
                //All players put a thumb on their forehead and say "Biscuit". Last player to do
                // so drinks and becomes the new "Biscuit".
                message = String.format("The sum was 7\n%s", getString(R.string.bizkit_message_sum_7));
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                        message);
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
                imageDialog.setView(layout);
                imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        getNewBizkitName("The last person to put their thumb on their head is now the Bizkit\n\n");
                    }
                });
                final AlertDialog dialog = imageDialog.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setTextColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.darkRed));
                    }
                });
                dialog.show();
            } else if (sum == 9) {
                //Person to right of roller drinks
                message = String.format("The sum was 9\n%s", getString(R.string.bizkit_message_sum_9));
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                        message);
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
                imageDialog.setView(layout);
                imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        setState(State.DispenseDrinks);
                    }
                });
                final AlertDialog dialog = imageDialog.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setTextColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.darkRed));
                    }
                });
                dialog.show();
            } else if (sum == 10) {
                //Roller drinks
                message = String.format("The sum was 10\n%s", getString(R.string.bizkit_message_sum_10));
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                        message);
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
                imageDialog.setView(layout);
                imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        setState(State.DispenseDrinks);
                    }
                });
                final AlertDialog dialog = imageDialog.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setTextColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.darkRed));
                    }
                });
                dialog.show();
            } else if (sum == 11) {
                //Person to left of roller drinks
                message = String.format("The sum was 11\n%s", getString(R.string.bizkit_message_sum_11));
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                        message);
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
                imageDialog.setView(layout);
                imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        setState(State.DispenseDrinks);
                    }
                });
                final AlertDialog dialog = imageDialog.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                                .setTextColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.darkRed));
                    }
                });
                dialog.show();
            }
            if (message.equals("")) message = "No action associated with this role";
            this.whatToDoTextView.setText(message);
        }
    }

    private void getNewBizkitName(String message) {
        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
        ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                String.format("%sWhat is the name of the new Bizkit?", message));
        final EditText editText = (EditText) layout.findViewById(R.id.popup_bizkit_youarebizkit_edittext);
        layout.findViewById(R.id.popup_bizkit_youarebizkit_edittext_container)
                .setVisibility(View.VISIBLE);
        layout.findViewById(R.id.popup_bizkit_diecontainer).setVisibility(View.GONE);
        imageDialog.setView(layout);
        imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bizkitName = editText.getText().toString().trim();
                dialog.dismiss();
                if (getSharedPreferences(getString(R.string.text_package), Context.MODE_PRIVATE)
                        .getBoolean(getString(R.string.settings_bizkit_insultbizkit), true))
                    Toast.makeText(getApplicationContext(),
                            String.format("Christ, %s, get your shit together.",
                                    bizkitName), Toast.LENGTH_LONG).show();
                setState(State.DispenseDrinks);
            }
        });
        final AlertDialog dialog = imageDialog.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setTextColor(ContextCompat.getColor(getApplicationContext(),
                                R.color.darkRed));
            }
        });
        dialog.show();
    }

    private void showPopupMessage(String message) {
        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup_global_showtext,
                (ViewGroup) findViewById(R.id.popup_global_showtext_root));
        ((TextView) layout.findViewById(R.id.popup_global_showtext_text)).setText(message);
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
                        .setTextColor(ContextCompat.getColor(getApplicationContext(),
                                R.color.darkRed));
            }
        });
        dialog.show();
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
                this.setState(State.FindBiscuit);
                return true;
            case R.id.rtb_settings:
                showSettingsPopup();
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
                (ViewGroup) findViewById(R.id.settingsscreen_scrollview_root));
        final View layout = DrinkingGamesGlobal.loadSettingsToLayout(l, prefs, this);
        layout.findViewById(R.id.settings_tabs_bizkit).callOnClick();
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

    enum State {FindBiscuit, DispenseDrinks}
}