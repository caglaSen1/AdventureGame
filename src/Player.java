import java.util.Scanner;

public class Player {
    private Characters character;
    private String playerName;
    private Scanner scan = new Scanner(System.in);

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public void pickChar(){
        Characters[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println("Characters:");
        System.out.println("--------------------------------------");
        for(Characters character : charList){
            System.out.println("Character " + character.getId() + ": "+ character.getCharName() +
                    "\t Damage" + ": " + character.getDamage() +
                    "\t Health" + ": " + character.getHealth() +
                    "\t Money" + ": " + character.getMoney());

        }
        System.out.println("--------------------------------------");
        System.out.println("Please pick a character (write the number):");


        boolean isPicked;
        do {
            switch (scan.nextInt()) {
                case 1:
                    character = new Samurai();
                    isPicked = true;
                    break;
                case 2:
                    isPicked = true;
                    character = new Knight();
                    break;
                case 3:
                    isPicked = true;
                    character = new Archer();
                    break;
                default:
                    System.out.println("Please write the number of the characters");
                    isPicked = false;
                    break;
            }

        }while (!isPicked);




        System.out.println("Your character is: " + character.getCharName() +
                "\t Damage" + ": " + character.getDamage() +
                "\t Health" + ": " + character.getHealth() +
                "\t Money" + ": " + character.getMoney());

    }



    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}


