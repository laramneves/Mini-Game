import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final int port;
    static List<ServerManager> clients = new ArrayList<>();

    public Server(int port) {
        this.port = port;
    }

    public int getClientsCount() {
        return clients.size();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New player connected: " + socket);

                ServerManager playerHandler = new ServerManager(socket, this);
                clients.add(playerHandler);

                if (clients.size() == 1) {
                    playerHandler.sendWaitingMessage();
                }
                // If there are two players connected, start the game
                if (clients.size() == 2) {
                    startGame();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void removeClient(ServerManager client) {
        clients.remove(client);
        System.out.println("Player disconnected: " + client.socket);
    }

    private void startGame() {
        // Get the first two players from the clients list
        ServerManager player1 = clients.get(0);
        ServerManager player2 = clients.get(1);

        // Notify players that the game is starting
        player1.sendMessage("Starting game...");
        player2.sendMessage("Starting game...");

        // Initialize the players' decks and hands
        Deck deckPlayer1 = new Deck();
        player1.player.drawInitialHand(deckPlayer1);
        Deck deckPlayer2 = new Deck();
        player2.player.drawInitialHand(deckPlayer2);

        // Start a new thread for the game
        Thread gameThread = new Thread(new Game(player1, player2));
        gameThread.start();

        // Clear the clients list for the next game
        clients.clear();
    }
}
