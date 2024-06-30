import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class ServerManager implements Runnable {

    final Socket socket;
    final Server server;
    PrintWriter writer;
    Player player;
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;
    static boolean test;
    static final LinkedList<Player> list = new LinkedList<>();

    public ServerManager(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.player = new Player(); // Assign a player name
        this.writer = new PrintWriter(socket.getOutputStream(), true);
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        if (server.getClientsCount() == 1) {
            sendWaitingMessage();
        }
    }


    public void sendWaitingMessage() {
        try {
            bufferedWriter.write("____    __    ____  ___       __  .___________. __  .__   __.   _______    \n" +
                    "\\   \\  /  \\  /   / /   \\     |  | |           ||  | |  \\ |  |  /  _____|   \n" +
                    " \\   \\/    \\/   / /  ^  \\    |  | `---|  |----`|  | |   \\|  | |  |  __     \n" +
                    "  \\            / /  /_\\  \\   |  |     |  |     |  | |  . `  | |  | |_ |    \n" +
                    "   \\    /\\    / /  _____  \\  |  |     |  |     |  | |  |\\   | |  |__| |    \n" +
                    "    \\__/  \\__/ /__/     \\__\\ |__|     |__|     |__| |__| \\__|  \\______|    \n" +
                    " _______   ______   .______                                                \n" +
                    "|   ____| /  __  \\  |   _  \\                                               \n" +
                    "|  |__   |  |  |  | |  |_)  |                                              \n" +
                    "|   __|  |  |  |  | |      /                                               \n" +
                    "|  |     |  `--'  | |  |\\  \\----.                                          \n" +
                    "|__|      \\______/  | _| `._____|                                          \n" +
                    ".___________. __    __   _______                                           \n" +
                    "|           ||  |  |  | |   ____|                                          \n" +
                    "`---|  |----`|  |__|  | |  |__                                             \n" +
                    "    |  |     |   __   | |   __|                                            \n" +
                    "    |  |     |  |  |  | |  |____                                           \n" +
                    "    |__|     |__|  |__| |_______|                                          \n" +
                    "  ______   .___________. __    __   _______ .______                        \n" +
                    " /  __  \\  |           ||  |  |  | |   ____||   _  \\                       \n" +
                    "|  |  |  | `---|  |----`|  |__|  | |  |__   |  |_)  |                      \n" +
                    "|  |  |  |     |  |     |   __   | |   __|  |      /                       \n" +
                    "|  `--'  |     |  |     |  |  |  | |  |____ |  |\\  \\----.                  \n" +
                    " \\______/      |__|     |__|  |__| |_______|| _| `._____|                  \n" +
                    ".______    __           ___   ____    ____  _______ .______                \n" +
                    "|   _  \\  |  |         /   \\  \\   \\  /   / |   ____||   _  \\               \n" +
                    "|  |_)  | |  |        /  ^  \\  \\   \\/   /  |  |__   |  |_)  |              \n" +
                    "|   ___/  |  |       /  /_\\  \\  \\_    _/   |   __|  |      /               \n" +
                    "|  |      |  `----. /  _____  \\   |  |     |  |____ |  |\\  \\----.          \n" +
                    "| _|      |_______|/__/     \\__\\  |__|     |_______|| _| `._____|         \n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());
            test = false;

            while (Server.clients.size() != 2) {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


                while (true) {
                    System.out.println("lock");
                    if (test) {
                        break;
                    }
                }
            }

            test = true;

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⣿⣾⣿⣷⣽⣾⣻⣿⣾⣧⣷⣿⣿⣿⣯⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⡿⣿⣿⣿⡿⣽⣿\n" +
                    "\"⣿⣻⣻⣿⣿⠿⠿⠿⠶⣶⣿⠿⠿⠿⢿⣿⣿⣿⣿⣿⣿⣿⡿⠿⣽⡿⣿⣿⣿⣅⣿⣟⣿⣿⡿⠵⣾⣷⣛⣿⡿⣿⣯⣿⣿⠟⠿⠻⠟⣿⠗⣿⣿⣿⣿⣿⣿⣿\n" +
                    "\"⣿⣿⣿⣿⣟⣻⣄⠀⠘⣿⣻⠟⢀⣾⣻⣿⣻⣿⣿⣿⣿⠏⢀⣀⡀⢠⣞⠉⠉⣿⣿⢿⠏⠁⣠⣤⠈⢹⣿⠉⠋⣿⣛⣛⣿⣧⠀⠀⣰⣿⢿⣟⣷⣿⣿⣿⣿⣿\n" +
                    "\"⡿⣿⣿⢿⣿⣯⣿⣦⡀⠈⠋⡰⠿⠿⡿⠿⠿⣿⡿⣻⠇⠀⣾⣫⡿⠾⡿⠷⠾⣯⡁⣿⠀⠀⣽⣷⠀⠸⣿⠀⠀⣯⣿⣿⣿⣿⠀⠀⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿\n" +
                    "\"⣿⣿⣿⣯⣷⣿⣷⣿⡷⠀⢸⣿⠀⠀⣿⠀⠀⡟⠛⢳⡆⠀⢿⣣⠀⠀⣿⠀⠀⡟⠛⢻⠀⠀⣻⣯⠀⢀⣿⠀⠀⠉⠀⢨⣿⣿⣆⢀⣿⣷⣯⣻⣿⣟⣿⣿⣿⣿\n" +
                    "\"⣛⣿⡟⣏⣿⢟⣿⣟⡧⠀⢸⣿⠀⠀⣿⠀⠀⣷⠶⣾⣧⠀⠘⣿⠆⠀⣿⠀⠀⣷⠶⢾⣄⠀⢻⡿⠀⢨⣟⠀⢰⡆⠀⢨⣷⡿⣿⢾⣯⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "\"⣷⣿⡿⣿⣿⣿⣟⣾⣟⣀⣘⣿⣄⣀⣉⣀⣀⣿⣶⣢⣺⣷⣀⣀⣄⡀⣿⣀⣀⣿⣶⣾⡽⣷⣀⣀⡴⣿⡏⠀⢸⣿⣄⣂⣟⣿⡁⢀⣿⣿⣻⣿⢿⣿⣿⣯⣿⣿\n" +
                    "\"⣽⣿⣟⣿⣿⣿⣿⣿⣿⣿⣿⣟⣿⣿⣿⣟⣛⣛⣛⣙⣭⣿⣿⣿⣿⢿⣿⣿⢿⣿⣷⣛⣿⣿⣿⣿⣿⣏⣿⣿⣿⣻⣿⣿⣿⣿⢿⣿⣻⡿⣿⣿⣿⡿⣿⣿⣿⣿");

            Prompt prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String[] options = {"YES", "NO"};
            MenuInputScanner menu = new MenuInputScanner(options);
            menu.setMessage("Ready for a duel?");

            int answer = prompt.getUserInput(menu);

            bufferedWriter.write("Player are you ready?\n" + options[answer - 1] + "\n");
            bufferedWriter.flush();

            StringInputScanner nameInput = new StringInputScanner();
            nameInput.setMessage("\nWhat's your name?\n");
            String playerName = prompt.getUserInput(nameInput);
            Player player = new Player();
            list.add(player);

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("You have 3000 health points\n");
            bufferedWriter.flush();
            bufferedWriter.write("Let's duel!\n");
            bufferedWriter.flush();


            // Main game loop
            String input;
            while ((input = reader.readLine()) != null) {
                System.out.println("Received from player: " + input);

                if (input.equalsIgnoreCase("quit")) {
                    server.removeClient(this);
                    break;
                } else if (input.equalsIgnoreCase("choose")) {
                    // Player wants to choose a card
                    Card chosenCard = player.chooseCard();
                    if (chosenCard != null) {
                        writer.println("You chose card: " + chosenCard.getName()); // Send confirmation to the client
                    }
                } // Inside the main game loop after the attack action
                if (input.equalsIgnoreCase("attack")) {
                    // Player wants to attack the other player
                    if (list.size() > 1) {
                        Player opponent = list.get(1); // Assuming there are always two players in the list
                        Card attackingCard = player.chooseCard();
                        if (attackingCard != null) {
                            AttackDone attackResult = player.attack(attackingCard, opponent);
                            bufferedWriter.write("You attacked " + opponent.getName() + " with " + attackingCard.getName()
                                    + ". Damage dealt: " + attackResult.getDamageDealt() +"\n");// Send confirmation to the client
                            bufferedWriter.flush();
                            bufferedWriter.write("Opponent's health: " + attackResult.getOpponentHealth() +"\n");
                            bufferedWriter.flush();
                            bufferedWriter.write("Your health: " + player.getHealth()+"\n");
                            bufferedWriter.flush();
                        }
                    } else {
                        bufferedWriter.write("There is no other player to attack. \n");
                        bufferedWriter.flush();
                    }
                }
                else {
                    // Handle other player actions
                    writer.println("Received: " + input); // Echo back to the client
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.removeClient(this);
            close();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
