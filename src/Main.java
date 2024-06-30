import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        int port = 8080;
        Server server = new Server(port);
        server.start();
        Card card = new Card();
        System.out.println(card.name);


        Player player1 = new Player();
        Player player2 = new Player();


        Deck deckPlayer1 = new Deck();
        player1.drawInitialHand(deckPlayer1);
        Deck deckPlayer2 = new Deck();
        player2.drawInitialHand(deckPlayer2);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Player 1 turn
            playerTurn(player1, scanner);

            // Check if the game has ended
            if (player2.getHealth() <= 0) {
                System.out.println(player2.getName() + " has lost the game!");
                break;
            }

            // Player 2 turn
            playerTurn(player2, scanner);

            // Check if the game has ended
            if (player1.getHealth() <= 0) {
                System.out.println(player1.getName() + " has lost the game!");
                break;
            }
        }
        scanner.close();
    }

    public static void playerTurn(Player player, Scanner scanner) {
        // Print player's current status
        System.out.println(player.getName() + "'s turn.");
        System.out.println("Health: " + player.getHealth());
        System.out.println("Hand: " + player.getHand());

        // Ask for user input
        System.out.println("Enter 'attack' to attack with a card: ");
        String input = scanner.nextLine();

        // Implement player's actions based on user input
        if (input.equalsIgnoreCase("attack")) {
            // For demonstration purposes, let's assume the player attacks itself with the first card in hand
            LinkedList<Card> hand = player.getHand();
            if (!hand.isEmpty()) {
                Card attackingCard = hand.getFirst();
                AttackDone damageDealt = player.attack(attackingCard, player);
                System.out.println("Health taken: " + damageDealt);
                System.out.println("Remaining health of " + player.getName() + ": " + player.getHealth());
            } else {
                System.out.println("No cards in hand to attack with.");
            }
        } else {
            System.out.println("Invalid command. Please enter 'attack' to perform an attack.");
        }
    }
}