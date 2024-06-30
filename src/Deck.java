import java.util.LinkedList;

public class Deck {

    static final int NUM_INITIAL_CARDS = 5;

    LinkedList<Card> cards = new LinkedList<>();

    public Deck() {
        createInitialDeck();
    }

    private void createInitialDeck() {
        for (int i = 0; i < NUM_INITIAL_CARDS; i++) {
            cards.add(createRandomCard());
        }
    }

    public LinkedList<Card> drawInitialHand() {
        LinkedList<Card> hand = new LinkedList<>();
        for (int i = 0; i < NUM_INITIAL_CARDS; i++) {
            hand.add(cards.removeFirst());
        }
        return hand;
    }

    private Card createRandomCard() {
        int health = (int) (Math.random() * 500);
        int damage = (int) (Math.random() * 500);
        String name = "Card" + (cards.size() + 1); // Generate a unique name for each card
        return CardFactory.createRandomCard(name, health, damage);
    }
}
