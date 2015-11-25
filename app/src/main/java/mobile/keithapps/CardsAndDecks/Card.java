package mobile.keithapps.CardsAndDecks;

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
                return R.string.settings_cod_ace_actionname_key;
            case TWO:
                return R.string.settings_cod_two_actionname_key;
            case THREE:
                return R.string.settings_cod_three_actionname_key;
            case FOUR:
                return R.string.settings_cod_four_actionname_key;
            case FIVE:
                return R.string.settings_cod_five_actionname_key;
            case SIX:
                return R.string.settings_cod_six_actionname_key;
            case SEVEN:
                return R.string.settings_cod_seven_actionname_key;
            case EIGHT:
                return R.string.settings_cod_eight_actionname_key;
            case NINE:
                return R.string.settings_cod_nine_actionname_key;
            case TEN:
                return R.string.settings_cod_ten_actionname_key;
            case JACK:
                return R.string.settings_cod_jack_actionname_key;
            case QUEEN:
                return R.string.settings_cod_queen_actionname_key;
            case KING:
                return R.string.settings_cod_king_actionname_key;
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
                return R.string.circleofdeath_carddirection_ace;
            case TWO:
                return R.string.circleofdeath_carddirection_two;
            case THREE:
                return R.string.circleofdeath_carddirection_three;
            case FOUR:
                return R.string.circleofdeath_carddirection_four;
            case FIVE:
                return R.string.circleofdeath_carddirection_five;
            case SIX:
                return R.string.circleofdeath_carddirection_six;
            case SEVEN:
                return R.string.circleofdeath_carddirection_seven;
            case EIGHT:
                return R.string.circleofdeath_carddirection_eight;
            case NINE:
                return R.string.circleofdeath_carddirection_nine;
            case TEN:
                return R.string.circleofdeath_carddirection_ten;
            case JACK:
                return R.string.circleofdeath_carddirection_jack;
            case QUEEN:
                return R.string.circleofdeath_carddirection_queen;
            case KING:
                return R.string.circleofdeath_carddirection_king;
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
                return R.string.settings_cod_ace_actiontext_key;
            case TWO:
                return R.string.settings_cod_two_actiontext_key;
            case THREE:
                return R.string.settings_cod_three_actiontext_key;
            case FOUR:
                return R.string.settings_cod_four_actiontext_key;
            case FIVE:
                return R.string.settings_cod_five_actiontext_key;
            case SIX:
                return R.string.settings_cod_six_actiontext_key;
            case SEVEN:
                return R.string.settings_cod_seven_actiontext_key;
            case EIGHT:
                return R.string.settings_cod_eight_actiontext_key;
            case NINE:
                return R.string.settings_cod_nine_actiontext_key;
            case TEN:
                return R.string.settings_cod_ten_actiontext_key;
            case JACK:
                return R.string.settings_cod_jack_actiontext_key;
            case QUEEN:
                return R.string.settings_cod_queen_actiontext_key;
            case KING:
                return R.string.settings_cod_king_actiontext_key;
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
                return R.string.circleofdeath_carddirection_description_ace;
            case TWO:
                return R.string.circleofdeath_carddirection_description_two;
            case THREE:
                return R.string.circleofdeath_carddirection_description_three;
            case FOUR:
                return R.string.circleofdeath_carddirection_description_four;
            case FIVE:
                return R.string.circleofdeath_carddirection_description_five;
            case SIX:
                return R.string.circleofdeath_carddirection_description_six;
            case SEVEN:
                return R.string.circleofdeath_carddirection_description_seven;
            case EIGHT:
                return R.string.circleofdeath_carddirection_description_eight;
            case NINE:
                return R.string.circleofdeath_carddirection_description_nine;
            case TEN:
                return R.string.circleofdeath_carddirection_description_ten;
            case JACK:
                return R.string.circleofdeath_carddirection_description_jack;
            case QUEEN:
                return R.string.circleofdeath_carddirection_description_queen;
            case KING:
                return R.string.circleofdeath_carddirection_description_king;
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
}
