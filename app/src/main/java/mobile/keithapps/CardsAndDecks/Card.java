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

    public int getDirections() {
        switch (this.getFaceValue()) {
            case ACE:
                return R.string.ace_directions;
            case TWO:
                return R.string.two_directions;
            case THREE:
                return R.string.three_directions;
            case FOUR:
                return R.string.four_directions;
            case FIVE:
                return R.string.five_directions;
            case SIX:
                return R.string.six_directions;
            case SEVEN:
                return R.string.seven_directions;
            case EIGHT:
                return R.string.eight_directions;
            case NINE:
                return R.string.nine_directions;
            case TEN:
                return R.string.ten_directions;
            case JACK:
                return R.string.jack_directions;
            case QUEEN:
                return R.string.queen_directions;
            case KING:
                return R.string.king_directions;
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
        if ((card == null && this != null) || (card != null && this == null)) return false;
        else if (card == null && this == null) return true;
        else if (this.getSuit().equals(card.getSuit()) && this.getSuit().equals(card.getSuit()))
            return true;
        return false;
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
