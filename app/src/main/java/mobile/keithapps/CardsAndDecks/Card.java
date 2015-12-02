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
     * The id of the card's drawable image
     */
    private int drawableId;
    /**
     * The text of the card
     */
    private String text;
    /**
     * The Card's suit
     */
    private Suit suit;

    /**
     * The constructor to make a Playing Card
     *
     * @param faceValue  The FaceValue of the Card
     * @param suit       The Suit of the Card
     * @param text       The Text of the Card
     * @param drawableId The id of the drawable image
     */
    public Card(FaceValue faceValue, Suit suit, String text, int drawableId) {
        this.suit = suit;
        this.faceValue = faceValue;
        this.drawableId = drawableId;
        this.text = text;
    }

    /**
     * Get the ID of the string of this Card's Action Name (Is stored in Shared Prefrences)
     *
     * @return the ID of the needed string
     */
    public int getActionNameKey() {
        switch (this.faceValue) {
            case ACE:
                return R.string.s_cod_ace_actionname_key;
            case TWO:
                return R.string.s_cod_two_actionname_key;
            case THREE:
                return R.string.s_cod_three_actionname_key;
            case FOUR:
                return R.string.s_cod_four_actionname_key;
            case FIVE:
                return R.string.s_cod_five_actionname_key;
            case SIX:
                return R.string.s_cod_six_actionname_key;
            case SEVEN:
                return R.string.s_cod_seven_actionname_key;
            case EIGHT:
                return R.string.s_cod_eight_actionname_key;
            case NINE:
                return R.string.s_cod_nine_actionname_key;
            case TEN:
                return R.string.s_cod_ten_actionname_key;
            case JACK:
                return R.string.s_cod_jack_actionname_key;
            case QUEEN:
                return R.string.s_cod_queen_actionname_key;
            case KING:
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
            case ACE:
                return R.string.cod_dir_a;
            case TWO:
                return R.string.cod_dir_2;
            case THREE:
                return R.string.cod_dir_3;
            case FOUR:
                return R.string.cod_dir_4;
            case FIVE:
                return R.string.cod_dir_5;
            case SIX:
                return R.string.cod_dir_6;
            case SEVEN:
                return R.string.cod_dir_7;
            case EIGHT:
                return R.string.cod_dir_8;
            case NINE:
                return R.string.cod_dir_9;
            case TEN:
                return R.string.cod_dir_10;
            case JACK:
                return R.string.cod_dir_j;
            case QUEEN:
                return R.string.cod_dir_q;
            case KING:
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
            case ACE:
                return R.string.s_cod_ace_actiontext_key;
            case TWO:
                return R.string.s_cod_two_actiontext_key;
            case THREE:
                return R.string.s_cod_three_actiontext_key;
            case FOUR:
                return R.string.s_cod_four_actiontext_key;
            case FIVE:
                return R.string.s_cod_five_actiontext_key;
            case SIX:
                return R.string.s_cod_six_actiontext_key;
            case SEVEN:
                return R.string.s_cod_seven_actiontext_key;
            case EIGHT:
                return R.string.s_cod_eight_actiontext_key;
            case NINE:
                return R.string.s_cod_nine_actiontext_key;
            case TEN:
                return R.string.s_cod_ten_actiontext_key;
            case JACK:
                return R.string.s_cod_jack_actiontext_key;
            case QUEEN:
                return R.string.s_cod_queen_actiontext_key;
            case KING:
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
            case ACE:
                return R.string.cod_des_a;
            case TWO:
                return R.string.cod_des_2;
            case THREE:
                return R.string.cod_des_3;
            case FOUR:
                return R.string.cod_des_4;
            case FIVE:
                return R.string.cod_des_5;
            case SIX:
                return R.string.cod_des_6;
            case SEVEN:
                return R.string.cod_des_7;
            case EIGHT:
                return R.string.cod_des_8;
            case NINE:
                return R.string.cod_des_9;
            case TEN:
                return R.string.cod_des_10;
            case JACK:
                return R.string.cod_des_j;
            case QUEEN:
                return R.string.cod_des_q;
            case KING:
                return R.string.cod_des_k;
        }
        return -1;
    }

    /**
     * Getter for the drawable id of the image
     *
     * @return The drawable id of the image for this card
     */
    public int getImageId() {
        return this.drawableId;
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
        return this.text;
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
     * Is Equal
     *
     * @param card card to compare to
     * @return true if the cards are equal
     */
    public boolean equals(Card card) {
        if ((card == null))
            return false;
        else if (this.getSuit().equals(card.getSuit()) && this.getSuit().equals(card.getSuit()))
            return true;
        return false;
    }

    /**
     * Get the numeric value of this card
     *
     * @param acesLow whether or not Aces are low
     * @return the numeric value of this card
     */
    public int getNumericValue(boolean acesLow) {
        switch (this.faceValue) {
            case ACE:
                return (acesLow) ? 1 : 14;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
                return 10;
            case JACK:
                return 11;
            case QUEEN:
                return 12;
            case KING:
                return 13;
        }
        return 0;
    }

    /**
     * The 13 face values of a playing card
     */
    public enum FaceValue {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    /**
     * The four suits of a playing card
     */
    public enum Suit {
        HEART, DIAMOND, CLUB, SPADE
    }

    public Drawable getDrawable(Context c, int skin){
        switch (this.faceValue){
            case ACE:
                switch (this.suit){
                    case HEART:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h1 : R.drawable.h1_2);
                    case DIAMOND:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d1 : R.drawable.d1_2);
                    case SPADE:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s1 : R.drawable.s1_2);
                    case CLUB:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c1 : R.drawable.c1_2);
                }
            case TWO:
                switch (this.suit){
                    case HEART:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h2 : R.drawable.h2_2);
                    case DIAMOND:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d2 : R.drawable.d2_2);
                    case SPADE:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s2 : R.drawable.s2_2);
                    case CLUB:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c2 : R.drawable.c2_2);
                }
            case THREE:
                switch (this.suit){
                    case HEART:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h3 : R.drawable.h3_2);
                    case DIAMOND:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d3 : R.drawable.d3_2);
                    case SPADE:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s3 : R.drawable.s3_2);
                    case CLUB:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c3 : R.drawable.c3_2);
                }
            case FOUR:
                switch (this.suit){
                    case HEART:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h4 : R.drawable.h4_2);
                    case DIAMOND:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d4 : R.drawable.d4_2);
                    case SPADE:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s4 : R.drawable.s4_2);
                    case CLUB:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c4 : R.drawable.c4_2);
                }
            case FIVE:
                switch (this.suit){
                    case HEART:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h5 : R.drawable.h5_2);
                    case DIAMOND:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d5 : R.drawable.d5_2);
                    case SPADE:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s5 : R.drawable.s5_2);
                    case CLUB:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c5 : R.drawable.c5_2);
                }
            case SIX:
                switch (this.suit){
                    case HEART:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h6 : R.drawable.h6_2);
                    case DIAMOND:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d6 : R.drawable.d6_2);
                    case SPADE:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s6 : R.drawable.s6_2);
                    case CLUB:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c6 : R.drawable.c6_2);
                }
            case SEVEN:
                switch (this.suit){
                    case HEART:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h7 : R.drawable.h7_2);
                    case DIAMOND:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d7 : R.drawable.d7_2);
                    case SPADE:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s7 : R.drawable.s7_2);
                    case CLUB:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c7 : R.drawable.c7_2);
                }
            case EIGHT:
                switch (this.suit){
                    case HEART:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h8 : R.drawable.h8_2);
                    case DIAMOND:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d8 : R.drawable.d8_2);
                    case SPADE:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s8 : R.drawable.s8_2);
                    case CLUB:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c8 : R.drawable.c8_2);
                }
            case NINE:
                switch (this.suit){
                    case HEART:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h9 : R.drawable.h9_2);
                    case DIAMOND:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d9 : R.drawable.d9_2);
                    case SPADE:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s9 : R.drawable.s9_2);
                    case CLUB:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c9 : R.drawable.c9_2);
                }
            case TEN:
                switch (this.suit){
                    case HEART:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h10 : R.drawable.h10_2);
                    case DIAMOND:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.d10 : R.drawable.d10_2);
                    case SPADE:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.s10 : R.drawable.s10_2);
                    case CLUB:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.c10 : R.drawable.c10_2);
                }
            case JACK:
                switch (this.suit){
                    case HEART:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.hj : R.drawable.hj_2);
                    case DIAMOND:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.dj : R.drawable.dj_2);
                    case SPADE:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.sj : R.drawable.sj_2);
                    case CLUB:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.cj : R.drawable.cj_2);
                }
            case QUEEN:
                switch (this.suit){
                    case HEART:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.hq : R.drawable.hq_2);
                    case DIAMOND:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.dq : R.drawable.dq_2);
                    case SPADE:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.sq : R.drawable.sq_2);
                    case CLUB:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.cq : R.drawable.cq_2);
                }
            case KING:
                switch (this.suit){
                    case HEART:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.hk : R.drawable.hk_2);
                    case DIAMOND:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.dk : R.drawable.dk_2);
                    case SPADE:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.sk : R.drawable.sk_2);
                    case CLUB:
                        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.ck : R.drawable.ck_2);
                }
        }
        return ContextCompat.getDrawable(c, (skin == 1) ? R.drawable.h1 : R.drawable.h1_2);
    }
}
