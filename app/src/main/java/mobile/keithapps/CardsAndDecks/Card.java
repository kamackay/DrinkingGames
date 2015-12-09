package mobile.keithapps.CardsAndDecks;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import mobile.keithapps.drinkinggames.R;

/**
 * Created by Keith on 2/15/2015.
 */
public class Card {
    /**
     * The Card's face value
     */
    private FaceValue faceValue;
    /**
     * The Card's suit
     */
    private Suit suit;

    /**
     * The constructor to make a Playing Card
     *
     * @param faceValue  The FaceValue of the Card
     * @param suit       The Suit of the Card
     */
    public Card(FaceValue faceValue, Suit suit) {
        this.suit = suit;
        this.faceValue = faceValue;
    }

    /**
     * Get the ID of the string of this Card's Action Name (Is stored in Shared Prefrences)
     *
     * @return the ID of the needed string
     */
    public int getActionNameKey() {
        switch (this.faceValue) {
            case Ace:
                return R.string.s_cod_ace_actionname_key;
            case Two:
                return R.string.s_cod_two_actionname_key;
            case Three:
                return R.string.s_cod_three_actionname_key;
            case Four:
                return R.string.s_cod_four_actionname_key;
            case Five:
                return R.string.s_cod_five_actionname_key;
            case Six:
                return R.string.s_cod_six_actionname_key;
            case Seven:
                return R.string.s_cod_seven_actionname_key;
            case Eight:
                return R.string.s_cod_eight_actionname_key;
            case Nine:
                return R.string.s_cod_nine_actionname_key;
            case Ten:
                return R.string.s_cod_ten_actionname_key;
            case Jack:
                return R.string.s_cod_jack_actionname_key;
            case Queen:
                return R.string.s_cod_queen_actionname_key;
            case King:
                return R.string.s_cod_king_actionname_key;
        }
        return -1;
    }

    /**
     * Get the ID of the string of this Card's Default Action Name (Is stored in Shared Prefrences)
     *
     * @return the ID of the needed string
     */
    public int getDefaultDirections() {
        switch (this.faceValue) {
            case Ace:
                return R.string.cod_dir_a;
            case Two:
                return R.string.cod_dir_2;
            case Three:
                return R.string.cod_dir_3;
            case Four:
                return R.string.cod_dir_4;
            case Five:
                return R.string.cod_dir_5;
            case Six:
                return R.string.cod_dir_6;
            case Seven:
                return R.string.cod_dir_7;
            case Eight:
                return R.string.cod_dir_8;
            case Nine:
                return R.string.cod_dir_9;
            case Ten:
                return R.string.cod_dir_10;
            case Jack:
                return R.string.cod_dir_j;
            case Queen:
                return R.string.cod_dir_q;
            case King:
                return R.string.cod_dir_k;
        }
        return -1;
    }

    /**
     * Get the ID of the string of this Card's Action Description (Is stored in Shared Prefrences)
     *
     * @return the ID of the needed string
     */
    public int getActionDescriptionKey() {
        switch (this.faceValue) {
            case Ace:
                return R.string.s_cod_ace_actiontext_key;
            case Two:
                return R.string.s_cod_two_actiontext_key;
            case Three:
                return R.string.s_cod_three_actiontext_key;
            case Four:
                return R.string.s_cod_four_actiontext_key;
            case Five:
                return R.string.s_cod_five_actiontext_key;
            case Six:
                return R.string.s_cod_six_actiontext_key;
            case Seven:
                return R.string.s_cod_seven_actiontext_key;
            case Eight:
                return R.string.s_cod_eight_actiontext_key;
            case Nine:
                return R.string.s_cod_nine_actiontext_key;
            case Ten:
                return R.string.s_cod_ten_actiontext_key;
            case Jack:
                return R.string.s_cod_jack_actiontext_key;
            case Queen:
                return R.string.s_cod_queen_actiontext_key;
            case King:
                return R.string.s_cod_king_actiontext_key;
        }
        return -1;
    }

    /**
     * Get the ID of the string of this Card's Default Action Description (Is stored in Shared Prefrences)
     *
     * @return the ID of the needed string
     */
    public int getDefaultActionDescription() {
        switch (this.faceValue) {
            case Ace:
                return R.string.cod_des_a;
            case Two:
                return R.string.cod_des_2;
            case Three:
                return R.string.cod_des_3;
            case Four:
                return R.string.cod_des_4;
            case Five:
                return R.string.cod_des_5;
            case Six:
                return R.string.cod_des_6;
            case Seven:
                return R.string.cod_des_7;
            case Eight:
                return R.string.cod_des_8;
            case Nine:
                return R.string.cod_des_9;
            case Ten:
                return R.string.cod_des_10;
            case Jack:
                return R.string.cod_des_j;
            case Queen:
                return R.string.cod_des_q;
            case King:
                return R.string.cod_des_k;
        }
        return -1;
    }

    /**
     * The suit of the card
     *
     * @return the card's suit
     */
    public Suit getSuit() {
        return this.suit;
    }

    /**
     * Get the String element of the card
     *
     * @return The String element of the Card
     */
    public String toString() {
        return String.format("%s of %s", this.faceValue.toString(), this.suit.toString());
    }

    /**
     * The value of the card
     *
     * @return the card's face value
     */
    public FaceValue getFaceValue() {
        return this.faceValue;
    }

    /**
     * Get the numeric value of this card
     *
     * @param acesLow whether or not Aces are low
     * @return the numeric value of this card
     */
    public int getNumericValue(boolean acesLow) {
        switch (this.faceValue) {
            case Ace:
                return (acesLow) ? 1 : 14;
            case Two:
                return 2;
            case Three:
                return 3;
            case Four:
                return 4;
            case Five:
                return 5;
            case Six:
                return 6;
            case Seven:
                return 7;
            case Eight:
                return 8;
            case Nine:
                return 9;
            case Ten:
                return 10;
            case Jack:
                return 11;
            case Queen:
                return 12;
            case King:
                return 13;
        }
        return 0;
    }

    public Drawable getDrawable(Context c) {
        int skin = c.getSharedPreferences(c.getString(R.string.text_package), Context.MODE_PRIVATE)
                .getInt(c.getString(R.string.s_general_cardskin), 1);
        switch (this.faceValue) {
            case Ace:
                switch (this.suit) {
                    case Hearts:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h1 : R.drawable.h1_2);
                    case Diamonds:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d1 : R.drawable.d1_2);
                    case Spades:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s1 : R.drawable.s1_2);
                    case Clubs:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c1 : R.drawable.c1_2);
                }
            case Two:
                switch (this.suit) {
                    case Hearts:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h2 : R.drawable.h2_2);
                    case Diamonds:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d2 : R.drawable.d2_2);
                    case Spades:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s2 : R.drawable.s2_2);
                    case Clubs:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c2 : R.drawable.c2_2);
                }
            case Three:
                switch (this.suit) {
                    case Hearts:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h3 : R.drawable.h3_2);
                    case Diamonds:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d3 : R.drawable.d3_2);
                    case Spades:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s3 : R.drawable.s3_2);
                    case Clubs:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c3 : R.drawable.c3_2);
                }
            case Four:
                switch (this.suit) {
                    case Hearts:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h4 : R.drawable.h4_2);
                    case Diamonds:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d4 : R.drawable.d4_2);
                    case Spades:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s4 : R.drawable.s4_2);
                    case Clubs:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c4 : R.drawable.c4_2);
                }
            case Five:
                switch (this.suit) {
                    case Hearts:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h5 : R.drawable.h5_2);
                    case Diamonds:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d5 : R.drawable.d5_2);
                    case Spades:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s5 : R.drawable.s5_2);
                    case Clubs:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c5 : R.drawable.c5_2);
                }
            case Six:
                switch (this.suit) {
                    case Hearts:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h6 : R.drawable.h6_2);
                    case Diamonds:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d6 : R.drawable.d6_2);
                    case Spades:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s6 : R.drawable.s6_2);
                    case Clubs:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c6 : R.drawable.c6_2);
                }
            case Seven:
                switch (this.suit) {
                    case Hearts:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h7 : R.drawable.h7_2);
                    case Diamonds:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d7 : R.drawable.d7_2);
                    case Spades:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s7 : R.drawable.s7_2);
                    case Clubs:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c7 : R.drawable.c7_2);
                }
            case Eight:
                switch (this.suit) {
                    case Hearts:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h8 : R.drawable.h8_2);
                    case Diamonds:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d8 : R.drawable.d8_2);
                    case Spades:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s8 : R.drawable.s8_2);
                    case Clubs:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c8 : R.drawable.c8_2);
                }
            case Nine:
                switch (this.suit) {
                    case Hearts:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h9 : R.drawable.h9_2);
                    case Diamonds:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d9 : R.drawable.d9_2);
                    case Spades:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s9 : R.drawable.s9_2);
                    case Clubs:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c9 : R.drawable.c9_2);
                }
            case Ten:
                switch (this.suit) {
                    case Hearts:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h10 : R.drawable.h10_2);
                    case Diamonds:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d10 : R.drawable.d10_2);
                    case Spades:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s10 : R.drawable.s10_2);
                    case Clubs:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c10 : R.drawable.c10_2);
                }
            case Jack:
                switch (this.suit) {
                    case Hearts:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.hj : R.drawable.hj_2);
                    case Diamonds:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.dj : R.drawable.dj_2);
                    case Spades:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.sj : R.drawable.sj_2);
                    case Clubs:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.cj : R.drawable.cj_2);
                }
            case Queen:
                switch (this.suit) {
                    case Hearts:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.hq : R.drawable.hq_2);
                    case Diamonds:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.dq : R.drawable.dq_2);
                    case Spades:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.sq : R.drawable.sq_2);
                    case Clubs:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.cq : R.drawable.cq_2);
                }
            case King:
                switch (this.suit) {
                    case Hearts:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.hk : R.drawable.hk_2);
                    case Diamonds:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.dk : R.drawable.dk_2);
                    case Spades:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.sk : R.drawable.sk_2);
                    case Clubs:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.ck : R.drawable.ck_2);
                }
        }
        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h1 : R.drawable.h1_2);
    }

    /**
     * The 13 face values of a playing card
     */
    public enum FaceValue {
        Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King
    }

    /**
     * The four suits of a playing card
     */
    public enum Suit {
        Hearts, Diamonds, Clubs, Spades
    }
}
