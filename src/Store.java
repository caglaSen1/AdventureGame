public class Store extends NormalLocation{
    public String pickedToolID;
    public String pickedWeaponID;
    public Weapon pickedWeapon;


    public Store(Player player) {
        super(player, "Store");
    }

    //PLAYER IS IN THE STORE
    @Override
    public boolean onLocation(){
        System.out.println("************ Welcome to the Store ************");
        System.out.println("1 - Weapons");
        System.out.println("2 - Armors");
        System.out.println("3 - Exit");
        System.out.println("***********************************************");

        System.out.println("Your choice: ");
        pickedToolID = scan.next();

        while(pickedToolID == "1" || pickedToolID == "2" || pickedToolID =="3"){
            System.out.println("You typed an invalid value! Please try again: ");
            pickedToolID = scan.next();
        }

        switch (pickedToolID){
            case "1" :
                printWeapon();
                break;
            case "2" :
                pickArmor();
                break;
            case "3" :
                 return true;
            default:
        }
        return true;
    }

    public void printPlayerMoney(){
        System.out.println("Your money is: " + this.getPlayer().getCharacter().getMoney());
    }

    //PRINTING WEAPONS
    public void printWeapon(){
        System.out.println("************ Weapons ************");
        printPlayerMoney();
        //weapons() is a static method
        for(Weapon weapon : Weapon.weapons()){
            System.out.println(weapon.getId() + "-" + weapon.getName() + " [Price: " + weapon.getPrice()
                    + " | " + "Damage: " + weapon.getDamage() + "]");
        }
        System.out.println("*********************************");

        //PICKING WEAPON
        pickWeapon();
    }

    //PICKING WEAPON
    public void pickWeapon(){
        System.out.println("Chose a weapon: ");
        pickedWeaponID = scan.next();
        while (!pickedWeaponID.equals("1") && !pickedWeaponID.equals("2") && !pickedWeaponID.equals("3")){
            System.out.println("You typed an invalid value! Please try again: ");
            pickedWeaponID = scan.next();
        }


        if(Integer.parseInt(pickedWeaponID) == (this.getPlayer().getCharacter().getInventory().getWeapon().getId())){
            System.out.println("You already have a " + this.getPlayer().getCharacter().getInventory().getWeapon().getName());
            pickWeapon();
        }
        //WEAPON PICKED

        //BUYING WEAPON
        buyWeapon();
    }

    //BUYING WEAPON
    public void buyWeapon(){
        pickedWeapon = Weapon.getWeaponByID(Integer.parseInt(pickedWeaponID));
        if(pickedWeapon != null){

            //MONEY CHECK
            if(pickedWeapon.getPrice() > this.getPlayer().getCharacter().getMoney()){
                System.out.println("Sorry, you haven't got enough money!!");
            }else{
                //WEAPON BOUGHT
                //System.out.println("You bought a " + pickedWeapon.getName());
                int newMoney = this.getPlayer().getCharacter().getMoney() - pickedWeapon.getPrice();
                this.getPlayer().getCharacter().setMoney(newMoney);
                //System.out.println("You have " + newMoney + " left");

                //PLAYER'S WEAPON INVENTORY CHANGED MESSAGE
                System.out.println("Previous weapon " + this.getPlayer().getCharacter().getInventory().getWeapon().getName()
                + ", changed with " + pickedWeapon.getName());

                //PICKED WEAPON ADDED TO THE INVENTORY OF PLAYER
                this.getPlayer().getCharacter().getInventory().setWeapon(pickedWeapon);

                //INCREASE DAMAGE MESSAGE
                int newDamage = (this.getPlayer().getCharacter().getDamage() - pickedWeapon.getDamage());
                System.out.println("Your damage increased by " + newDamage);
            }

        }
    }


    //PICKING AN ARMOR
    public void pickArmor(){
        System.out.println("************ Armors ************");
    }



}
