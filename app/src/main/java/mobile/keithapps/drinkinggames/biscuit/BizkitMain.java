package mobile.keithapps.drinkinggames.biscuit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
    private int rightDie, leftDie;
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
                this.whatToDoTextView.setText(getString(R.string.bizkit_welcometext));
                break;
            case DispenseDrinks:
                this.whatToDoTextView.setText(String.format("Now that %s (%s) is the bizkit, " +
                                "go counterclockwise and roll the dice, and each value will have" +
                                " an action that is associated!",
                        this.bizkitName, DrinkingGamesGlobal.getRandomInsult(getResources())));
                break;
        }
    }

    public void rollDie(View view) {
        Random rand = new Random();
        this.rightDie = rand.nextInt(6) + 1;
        this.leftDie = rand.nextInt(6) + 1;
        switch (this.rightDie) {
            case 1:
                this.rightDieView.setImageResource(R.drawable.die_1);
                break;
            case 2:
                this.rightDieView.setImageResource(R.drawable.die_2);
                break;
            case 3:
                this.rightDieView.setImageResource(R.drawable.die_3);
                break;
            case 4:
                this.rightDieView.setImageResource(R.drawable.die_4);
                break;
            case 5:
                this.rightDieView.setImageResource(R.drawable.die_5);
                break;
            case 6:
                this.rightDieView.setImageResource(R.drawable.die_6);
                break;
        }
        switch (this.leftDie) {
            case 1:
                this.leftDieView.setImageResource(R.drawable.die_1);
                break;
            case 2:
                this.leftDieView.setImageResource(R.drawable.die_2);
                break;
            case 3:
                this.leftDieView.setImageResource(R.drawable.die_3);
                break;
            case 4:
                this.leftDieView.setImageResource(R.drawable.die_4);
                break;
            case 5:
                this.leftDieView.setImageResource(R.drawable.die_5);
                break;
            case 6:
                this.leftDieView.setImageResource(R.drawable.die_6);
                break;
        }
        if (this.currState == State.FindBiscuit) {
            if ((this.leftDie + this.rightDie) == 7) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                }
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
                final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_bizkit_youarebizkit,
                        (ViewGroup) findViewById(R.id.popup_bizkit_youarebizkit_root));
                final EditText editText = (EditText) layout.findViewById(R.id.popup_bizkit_youarebizkit_edittext);
                imageDialog.setView(layout);
                imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bizkitName = editText.getText().toString().trim();
                        dialog.dismiss();
                        try {
                            Thread.sleep(1000);
                            Toast.makeText(getApplicationContext(),
                                    String.format("Christ, %s, get your shit together.",
                                            bizkitName), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                        }
                        setState(State.DispenseDrinks);
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
        } else {
            //TODO:Logic to see what has to happen for this die roll
        }
    }

    enum State {FindBiscuit, DispenseDrinks}
}