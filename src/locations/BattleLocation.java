package locations;
import items.StoreItems;
import items.*;
import obstacle.*;
import player.*;
import java.util.Random;

public abstract class BattleLocation extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    public Random random = new Random();
    private Weapon weaponAward;
    private Armor armorAward;
    private boolean isAwardCollected;



    public BattleLocation(Player player, String name, Obstacle obstacle, String award, int maxObstacle, int id) {
        super(player, name, id);
        setObstacle(obstacle);
        setAward(award);
        setMaxObstacle(maxObstacle);
        setIsAwardCollected(this.getPlayer().getCharacter().getInventory().isAwardCollected(award));

    }

    @Override
    public boolean onLocation() {

        //IF PLAYER WANTS TO GO A LOC, WE NEED TO CHECK IF HE/SHE COLLECTED TO ITEM OF THAT LOC. IF YES, CAN'T GO THAT LOC!!
        if(getIsAwardCollected() == false){
            int obsNum = this.randomObstacleNumber();
            System.out.println("You are in: " + this.getName());
            System.out.println("Careful! There are " + obsNum + " " + this.getObstacle().getName() + " " + "in here.");

            //FIGHT OR RUN
            System.out.println("<F> fight or <R> run: ");
            String selectCase = Location.scan.next().toUpperCase();
            //PLAYER CHOSE TO FIGHT
            if(selectCase.equals("F")){
                System.out.println("If you choose to fight, there is a 50% chance that either you or obstacle will attack first.");
                //FIGHT IS OVER
                if(fight(obsNum) == true){
                    //IF PLAYER DIED
                    if(this.getPlayer().getCharacter().getHealth() <= 0){
                        //GAME OVER!!!
                        return false;

                    }else{ //IF PLAYER DIDN'T DIE

                        //IF PLAYER IN MINE
                        if(this.getPlayer().getLocation().getName().equals("Mine")){
                            mineAward();
                        }else { //IF NOT IN MINE
                            System.out.println("You defeated all enemies in " + this.getPlayer().getLocation().getName());
                            System.out.println("You earned these awards: " + this.getAward() + " and " + " money (" + this.getObstacle().getGold() + ")");

                            //LOCATION AWARD
                            this.getPlayer().getCharacter().getInventory().setAwards(this.getAward());
                            this.getPlayer().printHowManyCollectInfo();

                            //MONEY AWARD
                            this.getPlayer().getCharacter().setMoney(this.getPlayer().getCharacter().getMoney() + this.getObstacle().getGold());
                            return true;
                        }
                    }

                    //IF "fight(obsNum) == false" MEANS PLAYER RUN FROM FIGHT
                }else{
                    return true;
                }

            }
            //PLAYER DIDN'T CHOOSE TO FIGHT
            return true;
        }else{//Player already collected the item of this loc so he/she couldn't get there.
            System.out.println("You already collected the award of " + getName() + ". So you can't go that location anymore.");
            return true;
        }
    }

    //FIGHT (RETURN TRUE IF PLAYER DIDN'T DIE)
    public boolean fight(int obsNum){
        for(int i = 1; i<= obsNum; i++){

            //NEW OBSTACLES HAVE FULL HEALTH
            this.getObstacle().setRemainingHealth(this.getObstacle().getBeginningHealth());


            while (this.getPlayer().getCharacter().getHealth() > 0 && this.getObstacle().getRemainingHealth() > 0){

                System.out.println("<A> attack or <R> run: ");
                String selectFight = Location.scan.next().toUpperCase();

                //PLAYER CHOSE TO ATTACK
                if(selectFight.equals("A")){

                    //WHO WILL ATTACK FIRST (if whoIsFirst = 0 player, if equals 1 obstacle attack first)
                    int whoIsFirst = random.nextInt(2);
                    if(whoIsFirst == 0){
                        playerAttackFirst(i);
                        obstacleAttackFirst(i);
                    }else if(whoIsFirst == 1){
                        obstacleAttackFirst(i);
                        playerAttackFirst(i);
                    }


                //PLAYER CHOSE TO RUN
                }else{
                    return false;
                }
            }
        }

        //FIGHT IS OVER
        return true;
    }

    public void playerAttackFirst(int whichObs) {

        //IF PLAYER DIDN'T DIE, IT WILL ATTACK TO THE OBSTACLE
        if (this.getPlayer().getCharacter().getHealth() > 0) {
            System.out.println("You attacked!!!");

            //OBSTACLE'S HEALTH DECREASED
            this.obstacle.setRemainingHealth(this.obstacle.getRemainingHealth() - this.getPlayer().getCharacter().getTotalDamage());

            //INFO AFTER HIT
            playerStats();
            obsStats(whichObs);
            System.out.println("");
            if(this.obstacle.getRemainingHealth() == 0){
                System.out.println("You killed " + whichObs + ". " + this.getObstacle().getName());
            }
            System.out.println("");
        }

    }


    public void obstacleAttackFirst(int whichObs){
        //IF OBSTACLE DIDN'T DIE, IT WILL ATTACK TO THE PLAYER
        if(this.getObstacle().getRemainingHealth() > 0){
            System.out.println(this.obstacle.getName() + " attacked!!!");

            //OBSTACLE'S DAMAGE (PLAYER MIGHT HAVE AN ARMOR)
            int obstaclesDamage = this.getObstacle().getDamage() - this.getPlayer().getCharacter().getBlocking();
            if(obstaclesDamage < 0){
                obstaclesDamage = 0;
            }
            //PLAYER'S HEALTH DECREASED
            int newHealth = this.getPlayer().getCharacter().getHealth() - obstaclesDamage;
            this.getPlayer().getCharacter().setHealth(newHealth);

            //INFO AFTER HIT
            //afterHit();
            playerStats();
            obsStats(whichObs);
            System.out.println("");
        }

    }

    // STATS OF PLAYER IN WAR
    public void playerStats(){
        System.out.println("      Your Stats:" +
                " | health: " + this.getPlayer().getCharacter().getHealth() +
                " | Weapon: " + this.getPlayer().getCharacter().getInventory().getWeapon().getName() +
                " | Damage: " + this.getPlayer().getCharacter().getDamage() +
                " | Armor: " + this.getPlayer().getCharacter().getInventory().getArmor().getName() +
                " | Blocking " + this.getPlayer().getCharacter().getBlocking() + " | ");
    }

    // STATS OF OBSTACLE IN WAR
    public void obsStats(int i){
        System.out.println("      " + i + ". " + this.getObstacle().getName() + "'s Stats:" +
                " | health: " + this.getObstacle().getRemainingHealth() +
                " | Damage: " + this.getObstacle().getDamage() +
                " | Award " + this.getObstacle().getGold() + " | ");

    }

    public void mineAward(){
        System.out.println("You defeated all enemies in " + this.getPlayer().getLocation().getName());
        int categoryChance  = random.nextInt(1,100);
        int weaponTypeChance = random.nextInt(1,100);
        int armorTypeChance = random.nextInt(1,100);
        int moneyAmountChance = random.nextInt(1,100);


        if(1<=categoryChance && categoryChance<=24){
            //Money(25%)
            if(1<=moneyAmountChance && moneyAmountChance<=19){
                //10(20%)
                System.out.println("You have won 10 coin!");
                this.getPlayer().getCharacter().setMoney(this.getPlayer().getCharacter().getMoney() + 10);
            } else if (20<=moneyAmountChance && moneyAmountChance<=49) {
                //5(30%)
                System.out.println("You have won 5 coin!");
                this.getPlayer().getCharacter().setMoney(this.getPlayer().getCharacter().getMoney() + 5);
            }else{
                //1(50%)
                System.out.println("You have won 1 coin!");
                this.getPlayer().getCharacter().setMoney(this.getPlayer().getCharacter().getMoney() + 1);
            }

        } else if (25<=categoryChance && categoryChance<=39) {
            //Weapon(15%)
            if(1<=weaponTypeChance && weaponTypeChance<=19){
                //Rifle(20%)
                System.out.println("You have won a rifle!");
                weaponAward = (Weapon) StoreItems.getItemByName("Rifle", Weapon.weapons());
                this.getPlayer().getCharacter().getInventory().setWeapon(weaponAward);
            }else if(20<=weaponTypeChance && weaponTypeChance<=49){
                //Sword(30%)
                System.out.println("You have won a sword!");
                weaponAward = (Weapon) StoreItems.getItemByName("Sword", Weapon.weapons());
                this.getPlayer().getCharacter().getInventory().setWeapon(weaponAward);
            }else{
                //Gun(50%)
                System.out.println("You have won a gun!");
                weaponAward = (Weapon) StoreItems.getItemByName("Gun", Weapon.weapons());
                this.getPlayer().getCharacter().getInventory().setWeapon(weaponAward);
            }
        } else if (40<=categoryChance && categoryChance<=54) {
            //Armor(15%)
            if(1<=armorTypeChance && armorTypeChance<=19){
                System.out.println("You have won a heavy armor!");
                armorAward = (Armor) StoreItems.getItemByName("Heavy armor", Armor.armors());
                this.getPlayer().getCharacter().getInventory().setArmor(armorAward);
                //Heavy armor(20%)
            }else if(20<=armorTypeChance && armorTypeChance<=49){
                //Medium armor(30%)
                System.out.println("You have won a medium armor!");
                armorAward = (Armor) StoreItems.getItemByName("Heavy armor", Armor.armors());
                this.getPlayer().getCharacter().getInventory().setArmor(armorAward);
            }else{
                //Light armor(50%)
                System.out.println("You have won a light armor!");
                armorAward = (Armor) StoreItems.getItemByName("Heavy armor", Armor.armors());
                this.getPlayer().getCharacter().getInventory().setArmor(armorAward);
            }

        }else {
            //Nothing(45%)
            System.out.println("You couldn't get an award from Mine :(");
        }

    }

    //RANDOMLY GIVES HOW MANY OBSTACLES
    public int randomObstacleNumber(){
        return random.nextInt(this.getMaxObstacle()) + 1;
    };

    public boolean getIsAwardCollected() {
        return isAwardCollected;
    }

    public void setIsAwardCollected(boolean awardCollected) {
        isAwardCollected = awardCollected;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        if(!award.equals("random")){
            this.award = award;

        }else{
            this.award = null;
        }

    }


}
