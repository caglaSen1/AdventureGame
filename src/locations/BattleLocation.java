package locations;
import obstacle.*;
import player.*;
import java.util.Random;

public abstract class BattleLocation extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    private Random random = new Random();
    //private int lengthofCollectedItemsArray = this.getPlayer().getCharacter().getInventory().getAwards().size();

    private boolean isAwardCollected;



    public BattleLocation(Player player, String name, Obstacle obstacle, String award, int maxObstacle, int id) {
        super(player, name, id);
        setObstacle(obstacle);
        setAward(award);
        setMaxObstacle(maxObstacle);
        //isAwardCollected = this.getPlayer().getCharacter().getInventory().awardCollected(award);
        setIsAwardCollected(this.getPlayer().getCharacter().getInventory().isAwardCollected(award));

        //IF PLAYER COLLECTED THE AWARD OF A LOCATION, COULDN'T GO THAT LOC. ANYMORE!
        /*if(this.getPlayer().getCharacter().getInventory().isThereAwardByName(award)){
            System.out.println("You already collected the award of " + name + ".");
            this.getPlayer().locChoosed = "";
            this.getPlayer().pickLocation();

        }*/

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
                        System.out.println("You defeated all enemies in " + this.getPlayer().getLocation().getName());
                        System.out.println("You earned these awards: " + this.getAward() + " and " + " money (" + this.getObstacle().getGold() + ")");


                        //LOCATION AWARD
                        this.getPlayer().getCharacter().getInventory().setAwards(this.getAward());
                        this.getPlayer().printHowManyCollectInfo();

                        //MONEY AWARD
                        this.getPlayer().getCharacter().setMoney(this.getPlayer().getCharacter().getMoney() + this.getObstacle().getGold());
                        return true;
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

            //STATS OF FIGHT


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

    //public void afterHit(){
        //System.out.println("Your health: " + this.getPlayer().getCharacter().getHealth());
        //System.out.println(this.getObstacle().getName() + "'s health: " + this.getObstacle().getRemainingHealth());

    //}

    //RANDOMLY GIVES HOW MANY OBSTACLES
    public int randomObstacleNumber(){
        return random.nextInt(this.getMaxObstacle()) + 1;
    }

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
        this.award = award;
    }

}
