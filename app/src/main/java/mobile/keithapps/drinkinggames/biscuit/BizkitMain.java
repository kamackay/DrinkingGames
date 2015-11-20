package mobile.keithapps.drinkinggames.biscuit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import mobile.keithapps.drinkinggames.DrinkingGamesGlobal;
import mobile.keithapps.drinkinggames.R;
import mobile.keithapps.drinkinggames.SettingsMain;

/**
 * Created by Keith on 11/19/2015.
 */
public class BizkitMain extends AppCompatActivity {
    private TextView whatToDoTextView;
    private ImageView leftDieView, rightDieView;
    private String bizkitName;
    private State currState;

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

        //Start Actually doing things
        this.setState(State.FindBiscuit);
    }

    private void setState(State state) {
        this.currState = state;
        switch (state) {
            case FindBiscuit:
                this.bizkitName = "";
                this.whatToDoTextView.setText(getString(R.string.bizkit_welcometext));
                break;
            case DispenseDrinks:
                this.whatToDoTextView.setText(String.format("Now that %s (%s) is the bizkit, " +
                                "go clockwise and roll the dice, and each value will have" +
                                " an action that is associated!",
                        this.bizkitName, DrinkingGamesGlobal.getRandomInsult(getResources())));
                break;
        }
    }

    public void rollDie(View view) {
        Random rand = new Random();
        int rightDie = rand.nextInt(6) + 1;
        int leftDie = rand.nextInt(6) + 1;
        this.rightDieView.setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
        this.leftDieView.setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
        if (this.currState == State.FindBiscuit) {
            if ((leftDie + rightDie) == 7) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    Log.e(getString(R.string.text_package), e.getMessage(), e);
                }
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                final EditText editText = (EditText) layout.findViewById(R.id.popup_bizkit_youarebizkit_edittext);
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
                        dialog.dismiss();
                        try {
                            //Thread.sleep(1000);
                            Toast.makeText(getApplicationContext(),
                                    String.format("Christ, %s, get your shit together.",
                                            bizkitName), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Log.e(getString(R.string.text_package), e.getMessage(), e);
                        }
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
            if (leftDie == rightDie) {
                if (leftDie == 1) {
                    AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                    final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                            (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                    ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext))
                            .setText("Two ones:\nEveryone takes a drink!");
                    ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                            .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                    ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                            .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
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
                } else if (leftDie == 6) {
                    //Roller has to invent a rule which will be applied for the rest of the game.
                    // The non respect of this rule will result to a drink.
                    AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                    final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                            (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                    ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                            String.format("%s%s%s", "Two sixes:\nRoller has to invent a rule which",
                                    " will be applied for the rest of the game.\n\nThe non respect",
                                    " of this rule will result to a drink."));
                    ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                            .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                    ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                            .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
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
                } else {
                    //Roller gives drinks to one or several players. ex. a 5-5 would mean the roller
                    // hands out 5 drinks
                    AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                    final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                            (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                    ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                            String.format("Two %d's:\nRoller can assign %d drinks to other players.",
                                    leftDie, sum));
                    ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                            .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                    ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                            .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
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
            } else if (sum == 3) {
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                        String.format("The sum was 3\n%s", getString(R.string.bizkit_message_sum_3)));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
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
            } else if (sum == 7) {
                //All players put a thumb on their forehead and say "Biscuit". Last player to do
                // so drinks and becomes the new "Biscuit".
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                        String.format("The sum was 7\n%s",
                                getString(R.string.bizkit_message_sum_7)));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
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
            } else if (sum == 9) {
                //Person to right of roller drinks
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                        String.format("The sum was 9\n%s", getString(R.string.bizkit_message_sum_9)));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
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
            } else if (sum == 10) {
                //Roller drinks
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                        String.format("The sum was 7\n%s", getString(R.string.bizkit_message_sum_10)));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
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
            } else if (sum == 11) {
                //Person to left of roller drinks
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                        String.format("The sum was 9\n%s", getString(R.string.bizkit_message_sum_11)));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
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
            } else {
                //Is this a thing??
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                        "Unaccounted for.");
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
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

            if (leftDie == 3 || rightDie == 3) {
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(
                        R.string.bizkit_message_three_present);
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
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
                Intent i = new Intent(getApplicationContext(), SettingsMain.class);
                startActivity(i);
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    enum State {FindBiscuit, DispenseDrinks}
}