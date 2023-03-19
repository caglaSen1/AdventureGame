import java.util.Scanner;

public class Game {

    private Scanner scan = new Scanner(System.in);

    public void start(){

        //Greeting
        System.out.println("Welcome Adventurer!");
        System.out.print("Please write your name: ");
        String playerName = scan.nextLine();

        Player player = new Player(playerName);

        System.out.println("Hi " + player.getPlayerName() + ", If you are ready, buckle up and start the adventure!!!");
        player.pickChar();





    }
}
