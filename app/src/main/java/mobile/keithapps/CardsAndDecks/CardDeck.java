package mobile.keithapps.CardsAndDecks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import mobile.keithapps.CardsAndDecks.Card.FaceValue;
import mobile.keithapps.CardsAndDecks.Card.Suit;

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
            this.reset();
            Random rand = new Random();
            int index = rand.nextInt(this.deck.size());
            return this.deck.remove(index);
        }
    }

    public void putBack(Card card) {
        this.deck.add(card);
        this.shuffle();
    }

    /**
     * Puts all 52 of the cards in the deck
     */
    public void setDeck() {
        this.deck.add(new Card(FaceValue.Ace, Suit.Clubs));
        this.deck.add(new Card(FaceValue.Ace, Suit.Spades));
        this.deck.add(new Card(FaceValue.Ace, Suit.Diamonds));
        this.deck.add(new Card(FaceValue.Ace, Suit.Hearts));
        this.deck.add(new Card(FaceValue.Two, Suit.Clubs));
        this.deck.add(new Card(FaceValue.Two, Suit.Spades));
        this.deck.add(new Card(FaceValue.Two, Suit.Diamonds));
        this.deck.add(new Card(FaceValue.Two, Suit.Hearts));
        this.deck.add(new Card(FaceValue.Three, Suit.Clubs));
        this.deck.add(new Card(FaceValue.Three, Suit.Spades));
        this.deck.add(new Card(FaceValue.Three, Suit.Diamonds));
        this.deck.add(new Card(FaceValue.Three, Suit.Hearts));
        this.deck.add(new Card(FaceValue.Four, Suit.Clubs));
        this.deck.add(new Card(FaceValue.Four, Suit.Spades));
        this.deck.add(new Card(FaceValue.Four, Suit.Diamonds));
        this.deck.add(new Card(FaceValue.Four, Suit.Hearts));
        this.deck.add(new Card(FaceValue.Five, Suit.Clubs));
        this.deck.add(new Card(FaceValue.Five, Suit.Spades));
        this.deck.add(new Card(FaceValue.Five, Suit.Diamonds));
        this.deck.add(new Card(FaceValue.Five, Suit.Hearts));
        this.deck.add(new Card(FaceValue.Six, Suit.Clubs));
        this.deck.add(new Card(FaceValue.Six, Suit.Spades));
        this.deck.add(new Card(FaceValue.Six, Suit.Diamonds));
        this.deck.add(new Card(FaceValue.Six, Suit.Hearts));
        this.deck.add(new Card(FaceValue.Seven, Suit.Clubs));
        this.deck.add(new Card(FaceValue.Seven, Suit.Spades));
        this.deck.add(new Card(FaceValue.Seven, Suit.Diamonds));
        this.deck.add(new Card(FaceValue.Seven, Suit.Hearts));
        this.deck.add(new Card(FaceValue.Eight, Suit.Clubs));
        this.deck.add(new Card(FaceValue.Eight, Suit.Spades));
        this.deck.add(new Card(FaceValue.Eight, Suit.Diamonds));
        this.deck.add(new Card(FaceValue.Eight, Suit.Hearts));
        this.deck.add(new Card(FaceValue.Nine, Suit.Clubs));
        this.deck.add(new Card(FaceValue.Nine, Suit.Spades));
        this.deck.add(new Card(FaceValue.Nine, Suit.Diamonds));
        this.deck.add(new Card(FaceValue.Nine, Suit.Hearts));
        this.deck.add(new Card(FaceValue.Ten, Suit.Clubs));
        this.deck.add(new Card(FaceValue.Ten, Suit.Spades));
        this.deck.add(new Card(FaceValue.Ten, Suit.Diamonds));
        this.deck.add(new Card(FaceValue.Ten, Suit.Hearts));
        this.deck.add(new Card(FaceValue.Jack, Suit.Clubs));
        this.deck.add(new Card(FaceValue.Jack, Suit.Spades));
        this.deck.add(new Card(FaceValue.Jack, Suit.Diamonds));
        this.deck.add(new Card(FaceValue.Jack, Suit.Hearts));
        this.deck.add(new Card(FaceValue.Queen, Suit.Clubs));
        this.deck.add(new Card(FaceValue.Queen, Suit.Spades));
        this.deck.add(new Card(FaceValue.Queen, Suit.Diamonds));
        this.deck.add(new Card(FaceValue.Queen, Suit.Hearts));
        this.deck.add(new Card(FaceValue.King, Suit.Clubs));
        this.deck.add(new Card(FaceValue.King, Suit.Spades));
        this.deck.add(new Card(FaceValue.King, Suit.Diamonds));
        this.deck.add(new Card(FaceValue.King, Suit.Hearts));
        this.shuffle();
    }

    public void reset() {
        this.deck = new ArrayList<>();
        this.setDeck();
    }
}