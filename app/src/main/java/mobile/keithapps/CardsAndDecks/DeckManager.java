package mobile.keithapps.CardsAndDecks;


/**
 * Created by Keith on 2/15/2015.
 */
public class DeckManager {
    private CardDeck deck;
    private CardDeck drawn;

    public DeckManager() {
        this.deck = new CardDeck();
        this.drawn = new CardDeck();
        this.deck.setDeck();
    }

    public Card drawCard() {
        Card toReturn = this.deck.drawRandom();
        this.drawn.add(toReturn);
        return toReturn;
    }

    public void restoreDeck() {
        while (!drawn.isDeckEmpty()) {
            Card card = drawn.getLast();
            this.drawn.remove(card);
            this.deck.add(card);
        }
    }

    public Card addLastCard() {
        Card card = this.drawn.getLast();
        this.drawn.remove(card);
        this.deck.add(card);
        return card;
    }
}
