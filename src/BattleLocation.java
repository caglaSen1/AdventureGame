import java.util.Random;

public abstract class BattleLocation extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

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
        String selectCase = scan.next();
        selectCase = selectCase.toUpperCase();
        if(selectCase.equals("F")){
            //savaşşşşş
        }

        return true;
    }

    //RANDOMLY GIVES HOW MANY OBSTACLES
    public int randomObstacleNumber(){
        Random random = new Random();
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
