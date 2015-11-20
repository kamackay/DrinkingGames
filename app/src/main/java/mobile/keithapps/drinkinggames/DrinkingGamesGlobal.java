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

    public static int getDrawableIdForDie(int dieVal) {
        switch (dieVal) {
            case 1:
                return R.drawable.die_1;
            case 2:
                return R.drawable.die_2;
            case 3:
                return R.drawable.die_3;
            case 4:
                return R.drawable.die_4;
            case 5:
                return R.drawable.die_5;
            case 6:
                return R.drawable.die_6;
        }
        return R.drawable.die_1;
    }
}
