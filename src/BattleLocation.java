import java.util.Random;

public abstract class BattleLocation extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    private Random random = new Random();

    public BattleLocation(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        setObstacle(obstacle);
        setAward(award);
        setMaxObstacle(maxObstacle);
    }

    @Override
    public boolean onLocation() {
        int obsNum = this.randomObstacleNumber();
        System.out.println("You are in: " + this.getName());
        System.out.println("Careful! There are " + obsNum + " " + this.getObstacle().getName() + " " + "in here.");

        //FIGHT OR RUN
        System.out.println("<F> fight or <R> run: ");
        String selectCase = scan.next().toUpperCase();
        //PLAYER CHOSE TO FIGHT
        if(selectCase.equals("F")){

            //FIGHT IS OVER
            if(fight(obsNum) == true){
                //IF PLAYER DIED
                if(this.getPlayer().getCharacter().getHealth() <= 0){
                    //GAME OVER!!!
                    return false;

                }else{ //IF PLAYER DIDN'T DIE
                    System.out.println("You defeated all enemies in " + this.getPlayer().getLocation().getName());
                    System.out.println("You earned these awards: " + this.getAward() + " and " + " money (" + this.getObstacle().getAward() + ")");

                    //LOCATION AWARD
                    this.getPlayer().getCharacter().getInventory().setAwards(this.getAward());

                    //MONEY AWARD
                    this.getPlayer().getCharacter().setMoney(this.getPlayer().getCharacter().getMoney() + this.getObstacle().getAward());
                    return true;
                }

                //IF "fight(obsNum) == false" MEANS PLAYER RUN FROM FIGHT
            }else{
                return true;
            }

        }

        //PLAYER DIDN'T CHOOSE TO FIGHT
        return true;
    }

    //FIGHT (RETURN TRUE IF PLAYER DIDN'T DIE)
    public boolean fight(int obsNum){
        for(int i = 1; i<= obsNum; i++){

            //NEW OBSTACLES HAVE FULL HEALTH
            this.getObstacle().setRemainingHealth(this.getObstacle().getBeginningHealth());

            //STATS OF FIGHT


            while (this.getPlayer().getCharacter().getHealth() > 0 && this.getObstacle().getRemainingHealth() > 0){

                System.out.println("If you choose to fight, there is a 50% chance that either you or obstacle will attack first.");
                System.out.println("<A> attack or <R> run: ");
                String selectFight = scan.next().toUpperCase();

                //PLAYER CHOSE TO ATTACK
                if(selectFight.equals("A")){

                    //WHO WILL ATTACK FIRST (if whoIsFırst = 0 player, if equals 1 obstacle attack first)
                    int whoIsFırst = random.nextInt(2);
                    if(whoIsFırst == 0){
                        playerAttack();
                    }else if(whoIsFırst == 1){
                        obstacleAttack();
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

    public void playerAttack() {

        //IF PLAYER DIDN'T DIE, IT WILL ATTACK TO THE OBSTACLE
        if (this.getPlayer().getCharacter().getHealth() > 0) {
            System.out.println("You attacked!!!");

            //OBSTACLE'S HEALTH DECREASED
            this.obstacle.setRemainingHealth(this.obstacle.getRemainingHealth() - this.getPlayer().getCharacter().getTotalDamage());

            //INFO AFTER HIT
            afterHit();
            System.out.println("");
        }
        obstacleAttack();
    }


    public void obstacleAttack(){
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
            afterHit();
            System.out.println("");
        }
        playerAttack();
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
                " | Award " + this.getObstacle().getAward() + " | ");

    }

    public void afterHit(){
        System.out.println("Your health: " + this.getPlayer().getCharacter().getHealth());
        System.out.println(this.getObstacle().getName() + "'s health: " + this.getObstacle().getRemainingHealth());
    }

    //RANDOMLY GIVES HOW MANY OBSTACLES
    public int randomObstacleNumber(){
        return random.nextInt(this.getMaxObstacle()) + 1;
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
