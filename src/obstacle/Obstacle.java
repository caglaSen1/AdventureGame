package obstacle;

public abstract class Obstacle {
    private int id;
    private int damage;
    private int remainingHealth;
    private String name;
    private int gold;

    //THIS HEALTH VALUE WON'T CHANGE
    private int beginningHealth;

    public Obstacle(int id, String name, int damage, int health, int award) {
        setId(id);
        setName(name);
        setDamage(damage);
        setRemainingHealth(health);
        setGold(award);
        setBeginningHealth(health);
    }

    public int getBeginningHealth() {
        return beginningHealth;
    }

    public void setBeginningHealth(int beginningHealth){
        this.beginningHealth = beginningHealth;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRemainingHealth() {
        return remainingHealth;
    }

    public void setRemainingHealth(int remainingHealth) {
       if(remainingHealth < 0){
           remainingHealth = 0;
       }
        this.remainingHealth = remainingHealth;
    }
}