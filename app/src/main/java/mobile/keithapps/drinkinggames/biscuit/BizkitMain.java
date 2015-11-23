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
    private TextView currentBizkitView;

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
                this.currentBizkitView.setText(String.format("Current Bizkit: %s (%s)",
                        this.bizkitName, DrinkingGamesGlobal.getRandomInsult(getResources())));
                break;
        }
    }

    public void rollDie(View view) {
        Random rand = new Random();
        final int rightDie = rand.nextInt(6) + 1;
        final int leftDie = rand.nextInt(6) + 1;
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
            String message = "";
            if (leftDie == rightDie) {
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
                message = String.format("The sum was 3\n%s", getString(R.string.bizkit_message_sum_3));
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
            } else if (sum == 7 && !(rightDie == 3 || leftDie == 3)) {
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
            if (leftDie == 3 || rightDie == 3) {
                String temp = String.format("If %s rolled this, he is no longer the Bizkit, and another Bizkit must be found. If %s did not roll this, then they have to drink once per '3' present\n\nWas %s the one that rolled this?",
                        bizkitName, bizkitName, bizkitName);
                message += temp;
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                ((TextView) layout.findViewById(R.id.popup_bizkit_messagetext)).setText(temp);
                ((ImageView) layout.findViewById(R.id.popup_bizkit_leftdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(leftDie));
                ((ImageView) layout.findViewById(R.id.popup_bizkit_rightdie))
                        .setImageResource(DrinkingGamesGlobal.getDrawableIdForDie(rightDie));
                imageDialog.setView(layout);
                imageDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        bizkitName = "";
                        setState(State.FindBiscuit);
                    }
                });
                imageDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showPopupMessage(String.format("In that case, %s has to take %d drinks",
                                bizkitName, ((leftDie + rightDie) == 6) ? 2 : 1));
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