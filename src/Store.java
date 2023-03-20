public class Store extends NormalLocation{
    //public String pickedToolID;
    public int pickedToolID;
    public int pickedWeaponID;
    public int pickedArmorID;

    //Player's weapon
    public Weapon weapon = this.getPlayer().getCharacter().getInventory().getWeapon();
    //Player's armor
    public Armor armor = this.getPlayer().getCharacter().getInventory().getArmor();

    //Picked weapon from the store
    public Weapon pickedWeapon;
    //Picked armor from the store
    public Armor pickedArmor;

    //PLAYER IS IN THE STORE
    public Store(Player player) {
        super(player, "Store");
        //this.getPlayer().getCharacter().getInventory();
    }

    @Override
    public boolean onLocation(){
        System.out.println("************ Welcome to the Store ************");
        System.out.println("1 - Weapons");
        System.out.println("2 - Armors");
        System.out.println("3 - Exit");
        System.out.println("***********************************************");

        System.out.println("Your choice: ");
        pickedToolID = scan.nextInt();

        //Checking if player typed an invalid value
        /*while(!(pickedToolID == "1" || pickedToolID == "2" || pickedToolID == "3")){
            System.out.println("You typed an invalid value! Please try again: ");
            pickedToolID = scan.next();
            System.out.println("picked tool id:" + pickedToolID);
        }*/


        switch (pickedToolID){
            case 1 :
                //PRINTING WEAPONS
                weapon.printItemsInfo();

                //PICK A WEAPON
                pickingWeapon();
                break;
            case 2 :
                //PRINTING ARMORS
                armor.printItemsInfo();

                //PICK AN ARMOR
                pickingArmor();

                break;
            case 3 :
                System.out.println("Come again :)");
                break;

        }
        return true;
    }


    //PICKING WEAPON
    public void pickingWeapon(){
        System.out.println("Chose a weapon: ");
        pickedWeaponID = scan.nextInt();

        //If typed 4 exit
        if(pickedWeaponID == 4){
            onLocation();
        }

        /*//Checking if player typed an invalid value
        while (pickedToolID != 1 && pickedToolID != 2 && pickedToolID != 3){
            System.out.println("You typed an invalid value! Please try again: ");
            pickedToolID = scan.nextInt();
        }*/

        //Player can't pick an armor that he/she already had.
        while(pickedWeaponID == (this.getPlayer().getCharacter().getInventory().getWeapon().getId())){
            System.out.println("You already have a " + this.getPlayer().getCharacter().getInventory().getWeapon().getName());
            pickingWeapon();
        }
            //WEAPON PICKED
            //BUYING WEAPON
            buyWeapon();



    }

    //BUYING WEAPON
    public void buyWeapon(){
        pickedWeapon = (Weapon) weapon.getItemByID(pickedWeaponID, weapon.weapons());


        if(pickedWeapon != null){

            //MONEY CHECK
            if(pickedWeapon.getPrice() > this.getPlayer().getCharacter().getMoney()){
                System.out.println("Sorry, you haven't got enough money!!");
                pickingWeapon();
            }else{
                //WEAPON BOUGHT

                //Price deducted from money
                int newMoney = this.getPlayer().getCharacter().getMoney() - pickedWeapon.getPrice();
                this.getPlayer().getCharacter().setMoney(newMoney);

                //PLAYER'S WEAPON INVENTORY CHANGED MESSAGE
                System.out.println("Previous weapon " + this.getPlayer().getCharacter().getInventory().getWeapon().getName()
                + ", changed with " + pickedWeapon.getName());

                //PICKED WEAPON ADDED TO THE INVENTORY OF THE PLAYER
                this.getPlayer().getCharacter().getInventory().setWeapon(pickedWeapon);

                //INCREASE DAMAGE MESSAGE
                int newDamage = (this.getPlayer().getCharacter().getDamage() - pickedWeapon.getDamage());
                System.out.println("Your damage changed by " + Math.abs(newDamage));
            }

        }
    }


    //PICKING AN ARMOR
    public void pickingArmor(){
        System.out.println("Chose an armor: ");
        pickedArmorID = scan.nextInt();

        //If typed 4 exit
        if(pickedArmorID== 4){
            onLocation();

        }

        //Checking if player typed an invalid value
        /*while (!pickedToolID.equals("1") && !pickedToolID.equals("2") && !pickedToolID.equals("3")){
            System.out.println("You typed an invalid value! Please try again: ");
            pickedToolID = scan.next();
        }*/

        //Player can't pick an armor that he/she already had.
        while(pickedArmorID == (this.getPlayer().getCharacter().getInventory().getArmor().getId())){
            System.out.println("You already have an " + this.getPlayer().getCharacter().getInventory().getArmor().getName());
            pickingArmor();
        }
            //ARMOR PICKED
            //BUYING ARMOR
            buyArmor();

    }

    //BUYING ARMOR
    public void buyArmor(){
        pickedArmor = (Armor) armor.getItemByID(pickedArmorID, armor.armors());

        if(pickedArmor != null){

            //MONEY CHECK
            if(pickedArmor.getPrice() > this.getPlayer().getCharacter().getMoney()){
                System.out.println("Sorry, you haven't got enough money!!");
                pickingArmor();
            }else{
                //ARMOR BOUGHT

                //Price deducted from money
                int newMoney = this.getPlayer().getCharacter().getMoney() - pickedArmor.getPrice();
                this.getPlayer().getCharacter().setMoney(newMoney);

                //PLAYER'S WEAPON INVENTORY CHANGED MESSAGE
                System.out.println("Previous armor " + this.getPlayer().getCharacter().getInventory().getArmor().getName()
                        + ", changed with " + pickedArmor.getName());

                //PICKED ARMOR ADDED TO THE INVENTORY OF THE PLAYER
                this.getPlayer().getCharacter().getInventory().setArmor(pickedArmor);

                //INCREASE DAMAGE MESSAGE
                int newBlocking = (this.getPlayer().getCharacter().getBlocking() - pickedArmor.getBlocking());
                System.out.println("Your blocking changed by " + Math.abs(newBlocking));
            }

        }
    }

}
