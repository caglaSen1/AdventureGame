import java.util.Scanner;

public class Game {
    private Player player;
    private Scanner scan = new Scanner(System.in);

    public void start(){

        //GREETING AND GETTING A NAME
        System.out.println("Welcome Adventurer!");
        System.out.print("Please write your name: ");
        String playerName =  " Çağla"; //scan.nextLine();

        //PLAYER INSTANTIATED
        player = new Player(playerName);

        System.out.println("Hi " + player.getPlayerName() + ", If you are ready, buckle up and start the adventure!!!");

        //CHAR PICKED
        player.pickChar();

        //LOCATION PICKED
        player.pickLocation();





    }
}
