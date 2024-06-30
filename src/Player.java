import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.BufferedWriter;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Player {



    String name;
    Deck deck;
    LinkedList<Card> hand;

    int health = 3000;
    BufferedWriter messagePlayer;


    public Player() throws IOException {
        this.health = health;
        System.out.println("You have " + health + " of health!");
        this.messagePlayer = new BufferedWriter(new FileWriter(FileDescriptor.out));
        messagePlayer.write("You have " + health + " of health!");
        messagePlayer.newLine();
        messagePlayer.flush();
        this.deck = new Deck();
        this.hand = deck.drawInitialHand();
    }

    public void drawInitialHand(Deck deck) {
        this.hand.addAll(deck.drawInitialHand());
    }

    public AttackDone attack(Card attackingCard, Player targetPlayer) {
        int damageDealt = attackingCard.getDamage();
        targetPlayer.health -= damageDealt;

        if (targetPlayer.health <= 0) {
            // If the target player's health drops below or equal to 0, adjust the damage accordingly
            damageDealt += targetPlayer.health;
            targetPlayer.health = 0;
        }

        // If the attacking card dealt any damage, print the attack information
        if (damageDealt > 0) {
            System.out.println("Attacked " + targetPlayer.name + " with " + attackingCard.getName()
                    + ". Damage dealt: " + damageDealt);
        } else {
            System.out.println(attackingCard.getName() + " couldn't deal damage to " + targetPlayer.name);
        }

        // Return an AttackResult object containing the damage dealt and the current health of the opponent
        return new AttackDone(damageDealt, targetPlayer.health);
    }

    public Card chooseCard() throws IOException {
        messagePlayer.write("Available cards:");
        messagePlayer.flush();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i+1) + ". " + hand.get(i).getName());
        }

        Prompt prompt = new Prompt(System.in, System.out);
        String[] options = new String[hand.size()];
        for (int i = 0; i < hand.size(); i++) {
            options[i] = String.valueOf(i + 1);
        }
        MenuInputScanner menu = new MenuInputScanner(options);
        menu.setMessage("Choose a card by entering its number:");
        int chosenIndex = prompt.getUserInput(menu);

        if (chosenIndex >= 1 && chosenIndex <= hand.size()) {
            return hand.get(chosenIndex - 1);
        } else {
            System.out.println("Invalid card choice.");
            return null;
        }
    }



    public void playCard(int index) {
        if (index >= 0 && index < hand.size()) {
            Card playedCard = hand.remove(index);
            System.out.println("Played " + playedCard.name);
        } else {
            System.out.println("Invalid card index.");
        }
    }
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public LinkedList<Card> getHand() {
        return hand;
    }
}
