import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.IOException;

public class Game implements Runnable {
    private ServerManager player1;
    private ServerManager player2;

    public Game(ServerManager player1, ServerManager player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void run() {
        // Game logic goes here
        // For demonstration purposes, let's assume a simple game loop
        while (true) {
            // Player 1's turn
            takeTurn(player1, player2);

            // Check if player 2 has lost
            if (player2.player.getHealth() <= 0) {
                endGame(player1, player2);
                break;
            }

            // Player 2's turn
            takeTurn(player2, player1);

            // Check if player 1 has lost
            if (player1.player.getHealth() <= 0) {
                endGame(player2, player1);
                break;
            }
        }
    }

    private void takeTurn(ServerManager currentPlayer, ServerManager opponentPlayer) {
        try {
            // Send message to the current player indicating their turn
            currentPlayer.sendMessage("Waiting for the opponent to take their turn...");

            // Implement the turn logic here, such as choosing a card to attack with
            // For example:
            Card chosenCard = currentPlayer.getPlayer().chooseCard();

            if (chosenCard != null) {
                // Perform the attack logic
                AttackDone attackResult = currentPlayer.getPlayer().attack(chosenCard, opponentPlayer.getPlayer());

                // Send attack result to both players
                currentPlayer.sendMessage("You attacked " + opponentPlayer.getPlayer().getName() + " with " +
                        chosenCard.getName() + ". Damage dealt: " + attackResult.getDamageDealt());
                opponentPlayer.sendMessage("You were attacked by " + currentPlayer.getPlayer().getName() + " with " +
                        chosenCard.getName() + ". Damage received: " + attackResult.getDamageDealt());
                currentPlayer.sendMessage("Opponent's health: " + attackResult.getOpponentHealth());
                opponentPlayer.sendMessage("Your health: " + opponentPlayer.getPlayer().getHealth());
            } else {
                // Handle if the player didn't choose a card
                currentPlayer.sendMessage("No card chosen. Turn skipped.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void endGame(ServerManager winner, ServerManager loser) {
        // Send message to the winner
        winner.sendMessage("Congratulations! You won the game.");

        // Send message to the loser
        loser.sendMessage("\n" +
                "▓██   ██▓ ▒█████   █    ██     ██▓     ▒█████    ██████ ▄▄▄█████▓   ▄▄▄█████▓ ██░ ██ ▓█████      ▄████  ▄▄▄       ███▄ ▄███▓▓█████  ▐██▌ \n" +
                " ▒██  ██▒▒██▒  ██▒ ██  ▓██▒   ▓██▒    ▒██▒  ██▒▒██    ▒ ▓  ██▒ ▓▒   ▓  ██▒ ▓▒▓██░ ██▒▓█   ▀     ██▒ ▀█▒▒████▄    ▓██▒▀█▀ ██▒▓█   ▀  ▐██▌ \n" +
                "  ▒██ ██░▒██░  ██▒▓██  ▒██░   ▒██░    ▒██░  ██▒░ ▓██▄   ▒ ▓██░ ▒░   ▒ ▓██░ ▒░▒██▀▀██░▒███      ▒██░▄▄▄░▒██  ▀█▄  ▓██    ▓██░▒███    ▐██▌ \n" +
                "  ░ ▐██▓░▒██   ██░▓▓█  ░██░   ▒██░    ▒██   ██░  ▒   ██▒░ ▓██▓ ░    ░ ▓██▓ ░ ░▓█ ░██ ▒▓█  ▄    ░▓█  ██▓░██▄▄▄▄██ ▒██    ▒██ ▒▓█  ▄  ▓██▒ \n" +
                "  ░ ██▒▓░░ ████▓▒░▒▒█████▓    ░██████▒░ ████▓▒░▒██████▒▒  ▒██▒ ░      ▒██▒ ░ ░▓█▒░██▓░▒████▒   ░▒▓███▀▒ ▓█   ▓██▒▒██▒   ░██▒░▒████▒ ▒▄▄  \n" +
                "   ██▒▒▒ ░ ▒░▒░▒░ ░▒▓▒ ▒ ▒    ░ ▒░▓  ░░ ▒░▒░▒░ ▒ ▒▓▒ ▒ ░  ▒ ░░        ▒ ░░    ▒ ░░▒░▒░░ ▒░ ░    ░▒   ▒  ▒▒   ▓▒█░░ ▒░   ░  ░░░ ▒░ ░ ░▀▀▒ \n" +
                " ▓██ ░▒░   ░ ▒ ▒░ ░░▒░ ░ ░    ░ ░ ▒  ░  ░ ▒ ▒░ ░ ░▒  ░ ░    ░           ░     ▒ ░▒░ ░ ░ ░  ░     ░   ░   ▒   ▒▒ ░░  ░      ░ ░ ░  ░ ░  ░ \n" +
                " ▒ ▒ ░░  ░ ░ ░ ▒   ░░░ ░ ░      ░ ░   ░ ░ ░ ▒  ░  ░  ░    ░           ░       ░  ░░ ░   ░      ░ ░   ░   ░   ▒   ░      ░      ░       ░ \n" +
                " ░ ░         ░ ░     ░            ░  ░    ░ ░        ░                        ░  ░  ░   ░  ░         ░       ░  ░       ░      ░  ░ ░    \n" +
                " ░ ░                                                                                                                                     \n.");

        // Perform any cleanup or post-game actions here
    }
}



