package mobile.keithapps.CardsAndDecks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import mobile.keithapps.CardsAndDecks.Card.FaceValue;
import mobile.keithapps.CardsAndDecks.Card.Suit;
import mobile.keithapps.drinkinggames.R;

/**
 * Created by Keith on 2/15/2015.
 */
public class CardDeck extends ArrayList {
    private ArrayList<Card> deck;

    public CardDeck() {
        this.deck = new ArrayList<>();
        this.setDeck();
    }

    /**
     * Shuffle all of the cards in the deck
     */
    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    /**
     * Getter for the number of cards in the deck
     *
     * @return the number of cards in the deck
     */
    public int cardsInDeck() {
        return this.deck.size();
    }

    /**
     * Determine whether or not the deck still has cards in it
     *
     * @return true if the deck is empty
     */
    public boolean isDeckEmpty() {
        return this.deck.isEmpty();
    }

    /**
     * Draw a random card from the deck
     */
    public Card drawRandom() {
        if (!this.isDeckEmpty()) {
            Random rand = new Random();
            int index = rand.nextInt(this.deck.size());
            return this.deck.remove(index);
        } else {
            return null;
        }
    }

    /**
     * Put a card back into the deck
     */
    public void putCardBackInDeck(Card returned) {
        for (Card card : this.deck) {
            if (returned.getFaceValue().equals(card.getFaceValue())
                    && returned.getSuit().equals(card.getSuit())) {
                return;
            }
        }
        this.deck.add(returned);
    }

    /**
     * Remove all cards of a certain suit
     *
     * @param suit the suit to be removed from the deck
     */
    public void removeAll(Suit suit) {
        for (Card card : this.deck) {
            if (card.getSuit().equals(suit)) {
                this.deck.remove(card);
            }
        }
    }

    /**
     * Remove all cards with a set face value
     *
     * @param faceValue the face value to be removed from the deck
     */
    public void removeAll(FaceValue faceValue) {
        for (Card card : this.deck) {
            if (card.getFaceValue().equals(faceValue)) {
                this.deck.remove(card);
            }
        }
    }

    /**
     * Remove a card from the deck
     *
     * @param faceValue facevalue of the card to remove
     * @param suit      suit of the card to remove
     */
    public void remove(FaceValue faceValue, Suit suit) {
        for (Card card : this.deck) {
            if (card.getFaceValue().equals(faceValue) && card.getSuit().equals(suit)) {
                this.deck.remove(card);
            }
        }
    }

    /**
     * Remove a card from the deck
     *
     * @param card card to remove
     */
    public void remove(Card card) {
        FaceValue faceValue = card.getFaceValue();
        Suit suit = card.getSuit();
        for (Card c : this.deck) {
            if (c.getFaceValue().equals(faceValue) && c.getSuit().equals(suit)) {
                this.deck.remove(card);
            }
        }
    }

    /**
     * Get the last card in the deck
     *
     * @return last card in the deck
     */
    public Card getLast() {
        return this.getCardAt(this.cardsInDeck() - 1);
    }

    /**
     * @param index Index of the card
     * @return Card at the given index
     */
    public Card getCardAt(int index) {
        return this.deck.get(index);
    }

    /**
     * Add a card to the deck
     *
     * @param card the card to add
     * @return false if the card was already in the deck, or was false
     */
    public boolean add(Card card) {
        if (card.equals(null)) return false;
        for (Card inDeck : deck) {
            if (inDeck.equals(card)) return false;
        }
        this.deck.add(card);
        return true;
    }

    /**
     * Puts all 52 of the cards in the deck
     */
    public void setDeck() {
        this.deck.add(new Card(FaceValue.ACE, Suit.CLUB, "Ace of Clubs", R.drawable.c1));
        this.deck.add(new Card(FaceValue.ACE, Suit.SPADE, "Ace of Spades", R.drawable.s1));
        this.deck.add(new Card(FaceValue.ACE, Suit.DIAMOND, "Ace of Diamonds", R.drawable.d1));
        this.deck.add(new Card(FaceValue.ACE, Suit.HEART, "Ace of Hearts", R.drawable.h1));
        this.deck.add(new Card(FaceValue.TWO, Suit.CLUB, "Two of Clubs", R.drawable.c2));
        this.deck.add(new Card(FaceValue.TWO, Suit.SPADE, "Two of Spades", R.drawable.s2));
        this.deck.add(new Card(FaceValue.TWO, Suit.DIAMOND, "Two of Diamonds", R.drawable.d2));
        this.deck.add(new Card(FaceValue.TWO, Suit.HEART, "Two of Hearts", R.drawable.h2));
        this.deck.add(new Card(FaceValue.THREE, Suit.CLUB, "Three of Clubs", R.drawable.c3));
        this.deck.add(new Card(FaceValue.THREE, Suit.SPADE, "Three of Spades", R.drawable.s3));
        this.deck.add(new Card(FaceValue.THREE, Suit.DIAMOND, "Three of Diamonds", R.drawable.d3));
        this.deck.add(new Card(FaceValue.THREE, Suit.HEART, "Three of Hearts", R.drawable.h3));
        this.deck.add(new Card(FaceValue.FOUR, Suit.CLUB, "Four of Clubs", R.drawable.c4));
        this.deck.add(new Card(FaceValue.FOUR, Suit.SPADE, "Four of Spades", R.drawable.s4));
        this.deck.add(new Card(FaceValue.FOUR, Suit.DIAMOND, "Four of Diamonds", R.drawable.d4));
        this.deck.add(new Card(FaceValue.FOUR, Suit.HEART, "Four of Hearts", R.drawable.h4));
        this.deck.add(new Card(FaceValue.FIVE, Suit.CLUB, "Five of Clubs", R.drawable.c5));
        this.deck.add(new Card(FaceValue.FIVE, Suit.SPADE, "Five of Spades", R.drawable.s5));
        this.deck.add(new Card(FaceValue.FIVE, Suit.DIAMOND, "Five of Diamonds", R.drawable.d5));
        this.deck.add(new Card(FaceValue.FIVE, Suit.HEART, "Five of Hearts", R.drawable.h5));
        this.deck.add(new Card(FaceValue.SIX, Suit.CLUB, "Six of Clubs", R.drawable.c6));
        this.deck.add(new Card(FaceValue.SIX, Suit.SPADE, "Six of Spades", R.drawable.s6));
        this.deck.add(new Card(FaceValue.SIX, Suit.DIAMOND, "Six of Diamonds", R.drawable.d6));
        this.deck.add(new Card(FaceValue.SIX, Suit.HEART, "Six of Hearts", R.drawable.h6));
        this.deck.add(new Card(FaceValue.SEVEN, Suit.CLUB, "Seven of Clubs", R.drawable.c7));
        this.deck.add(new Card(FaceValue.SEVEN, Suit.SPADE, "Seven of Spades", R.drawable.s7));
        this.deck.add(new Card(FaceValue.SEVEN, Suit.DIAMOND, "Seven of Diamonds", R.drawable.d7));
        this.deck.add(new Card(FaceValue.SEVEN, Suit.HEART, "Seven of Hearts", R.drawable.h7));
        this.deck.add(new Card(FaceValue.EIGHT, Suit.CLUB, "Eight of Clubs", R.drawable.c8));
        this.deck.add(new Card(FaceValue.EIGHT, Suit.SPADE, "Eight of Spades", R.drawable.s8));
        this.deck.add(new Card(FaceValue.EIGHT, Suit.DIAMOND, "Eight of Diamonds", R.drawable.d8));
        this.deck.add(new Card(FaceValue.EIGHT, Suit.HEART, "Eight of Hearts", R.drawable.h8));
        this.deck.add(new Card(FaceValue.NINE, Suit.CLUB, "Nine of Clubs", R.drawable.c9));
        this.deck.add(new Card(FaceValue.NINE, Suit.SPADE, "Nine of Spades", R.drawable.s9));
        this.deck.add(new Card(FaceValue.NINE, Suit.DIAMOND, "Nine of Diamonds", R.drawable.d9));
        this.deck.add(new Card(FaceValue.NINE, Suit.HEART, "Nine of Hearts", R.drawable.h9));
        this.deck.add(new Card(FaceValue.TEN, Suit.CLUB, "Ten of Clubs", R.drawable.c10));
        this.deck.add(new Card(FaceValue.TEN, Suit.SPADE, "Ten of Spades", R.drawable.s10));
        this.deck.add(new Card(FaceValue.TEN, Suit.DIAMOND, "Ten of Diamonds", R.drawable.d10));
        this.deck.add(new Card(FaceValue.TEN, Suit.HEART, "Ten of Hearts", R.drawable.h10));
        this.deck.add(new Card(FaceValue.JACK, Suit.CLUB, "Jack of Clubs", R.drawable.cj));
        this.deck.add(new Card(FaceValue.JACK, Suit.SPADE, "Jack of Spades", R.drawable.sj));
        this.deck.add(new Card(FaceValue.JACK, Suit.DIAMOND, "Jack of Diamonds", R.drawable.dj));
        this.deck.add(new Card(FaceValue.JACK, Suit.HEART, "Jack of Hearts", R.drawable.hj));
        this.deck.add(new Card(FaceValue.QUEEN, Suit.CLUB, "Queen of Clubs", R.drawable.cq));
        this.deck.add(new Card(FaceValue.QUEEN, Suit.SPADE, "Queen of Spades", R.drawable.sq));
        this.deck.add(new Card(FaceValue.QUEEN, Suit.DIAMOND, "Queen of Diamonds", R.drawable.dq));
        this.deck.add(new Card(FaceValue.QUEEN, Suit.HEART, "Queen of Hearts", R.drawable.hq));
        this.deck.add(new Card(FaceValue.KING, Suit.CLUB, "King of Hearts", R.drawable.hk));
        this.deck.add(new Card(FaceValue.KING, Suit.SPADE, "King of Spades", R.drawable.sk));
        this.deck.add(new Card(FaceValue.KING, Suit.DIAMOND, "King of Diamonds", R.drawable.dk));
        this.deck.add(new Card(FaceValue.KING, Suit.HEART, "King of Hearts", R.drawable.hk));
        this.shuffle();
    }

    public void reset() {
        this.deck = new ArrayList<>();
        this.setDeck();
    }
}