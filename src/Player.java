import java.util.Scanner;

public class Player {
    private Characters character;
    private Location location;
    //private Inventory inventory;
    public boolean noLocPickedYet = true;
    private String playerName;
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
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("1 - Safe House - There are no enemies in Safe House and you can restored your health.");
            System.out.println("2 - Store - You can buy a weapon or armor in Store.");
            System.out.println("0 - Quit the game");
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("Please pick a location to move: ");

            // If player pressed different nums then 2.. it goes to the Safe House!
            switch (scan.next()) {
                case "0":
                    location = null;
                    break;
                case "2":
                    location = new Store(this);
                    break;
                default:
                    location = new SafeHouse(this);
                    break;
            }
            noLocPickedYet = false;
            if(location == null){
                System.out.println("See you later, adventurer. The adventure is not over...");
                break;
            }
        }

        if(location != null){
            //If location.onLocation = false, it means that player died.
            System.out.println("GAME OVER!!!");
        }
    }

    public void printInfo(){
        System.out.println("");
        System.out.println("************************************************************************************************");
        System.out.println("| " + character.getCharName() +
                " | Weapon: " + character.getInventory().getWeapon().getName() +
                " | Damage:" + " " + character.getDamage() +
                " | Armor:" + " " + character.getInventory().getArmor().getName() +
                " | Blocking:" + " " + character.getBlocking() +
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


