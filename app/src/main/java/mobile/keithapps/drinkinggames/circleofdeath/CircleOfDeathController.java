package mobile.keithapps.drinkinggames.circleofdeath;


import mobile.keithapps.CardsAndDecks.Card;
import mobile.keithapps.CardsAndDecks.CardDeck;

/**
 * Created by Keith on 2/15/2015.
 */
public class CircleOfDeathController {
    private CardDeck deck;
    private CardDeck drawn;

    public CircleOfDeathController() {
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
