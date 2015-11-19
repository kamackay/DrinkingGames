package mobile.keithapps.drinkinggames;

import android.content.res.Resources;

import java.util.Random;

/**
 * Created by Keith on 11/19/2015.
 */
public class DrinkingGamesGlobal {
    public static String getRandomInsult(Resources res) {
        String[] insults = res.getStringArray(R.array.insults);
        return insults[new Random().nextInt(insults.length)];
    }
}
