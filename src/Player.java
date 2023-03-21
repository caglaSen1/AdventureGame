import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String playerName;
    private Characters character;
    private Location location;
    public boolean noLocPickedYet = true;
    public boolean isExit = false;
    private Scanner scan = new Scanner(System.in);


    public Player(String playerName) {
        setPlayerName(playerName);

    }

    public void pickChar(){

        //SHOW CHARACTER INFOS
        Characters[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println("Characters:");
        System.out.println("--------------------------------------------------------------");
        for(Characters character : charList){
            System.out.println("Character " + character.getId() + ": "+ character.getCharName() +
                    "\t Damage" + ": " + character.getDamage() +
                    "\t Health" + ": " + character.getHealth() +
                    "\t Money" + ": " + character.getMoney());

        }
        System.out.println("--------------------------------------------------------------");
        System.out.println("Please pick a character (write the number):");


        //PICK A CHARACTER
        boolean isCharPicked;
        do {
            switch (scan.next()) {
                case "1":
                    isCharPicked = true;
                    character = new Samurai();
                    break;
                case "2":
                    isCharPicked = true;
                    character = new Archer();
                    break;
                case "3":
                    isCharPicked = true;
                    character = new Knight();
                    break;
                default:
                    System.out.println("You typed an invalid value! Please type the number of the characters");
                    isCharPicked = false;
                    break;
            }

        }while (!isCharPicked);

    }


    //PICK A LOCATION
    public void pickLocation(){

        //If location.onLocation = true continue or player pick a loc for first time continue.
        while (noLocPickedYet || location.onLocation()){
            //PRINTING PLAYER'S INFOS
            printInfo();

            System.out.println("Locations:");
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("1 - Safe House - There are no enemies in Safe House and you can restored your health.");
            System.out.println("2 - Store - You can buy a weapon or armor in Store.");
            System.out.println("3 - Cave - You can find food in the cave. But be careful there are zombies.");
            System.out.println("4 - Forest - You can find wood in the forest. But be careful there are vampires.");
            System.out.println("5 - River - You can find water in the river. But be careful there are bears.");
            System.out.println("0 - Quit the game");
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("Please pick a location to move: ");

            // If player pressed different nums then 2.. it goes to the Safe House!
            switch (scan.next()) {
                case "0":
                    isExit = true;
                    break;
                case "2":
                    location = new Store(this);
                    break;
                case "3":
                    location = new Cave(this);
                    break;
                case "4":
                    location = new Forest(this);
                    break;
                case "5":
                    location = new River(this);
                    break;
                default:
                    location = new SafeHouse(this);
                    break;
            }

            noLocPickedYet = false;
            if(isExit == true){
                System.out.println("See you later, adventurer. The adventure is not over...");
                break;
            }

        }

        if(location != null){
            //If location.onLocation = false, it means that player died.
            System.out.println("GAME OVER!!!");
        }

        //IF PLAYER COLLECT ALL AWARDS

    }

    public void printInfo(){
        System.out.println("");
        System.out.println("************************************************************************************************");
        System.out.println("| " + character.getCharName() +
                " | Weapon: " + character.getInventory().getWeapon().getName() +
                " | Damage:" + " " + character.getTotalDamage() +
                " | Armor:" + " " + character.getInventory().getArmor().getName() +
                " | Blocking:" + " " + character.getTotalBlocking() +
                " | Health:" + " " + character.getHealth() +
                " | Money:" + " " + character.getMoney() + " |");
        System.out.println("************************************************************************************************");
        System.out.println("");
    }

    public Characters getCharacter() {
        return character;
    }


    public Location getLocation() {
        return location;
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


}


