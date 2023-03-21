import player.Player;

import java.util.Scanner;

public class Game {
    private Player player;
    private Scanner scan = new Scanner(System.in);

    public void start(){

        //GREETING AND GETTING A NAME
        System.out.println("Welcome Adventurer!");
        System.out.print("Please write your name: ");
        String playerName = scan.nextLine();

        //PLAYER INSTANTIATED
        player = new Player(playerName);

        System.out.println("Hi " + player.getPlayerName() + ", If you are ready, buckle up and start the adventure!!!");
        System.out.println("Remember if you want to win you need to collect food, wood and water.");

        //CHAR PICKED
        player.pickChar();

        //LOCATION PICKED
        player.pickLocation();





    }
}
