package player;

public abstract class Characters {
    private int id;
    private String charName;
    private int money;
    private Inventory inventory;

    private int health;
    //This value won't change
    private int beginningHealth;

    //POWER OF DAMAGING AND BLOCKING
    private int damage;
    private int blocking;


    public Characters(int id, String charName, int damage, int blocking, int health, int money) {
        setId(id);
        setCharName(charName);
        setDamage(damage);
        setBlocking(blocking);
        setHealth(health);
        setMoney(money);
        setBeginningHealth(health);

        //WHEN pickChar() START, PLAYER GETS AN INVENTORY
        setInventory(new Inventory());
    }

    //TOTAL DAMAGE POWER OF PLAYER
    public int getTotalDamage() {
        return damage + inventory.getWeapon().getDamage();
    }

    //TOTAL BLOCKING POWER OF PLAYER
    public int getTotalBlocking() {
        return blocking + inventory.getArmor().getBlocking();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getDamage() {
        return damage;
    }

    public int getBeginningHealth() {
        return beginningHealth;
    }

    public void setBeginningHealth(int beginningHealth) {
        this.beginningHealth = beginningHealth;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health < 0){
            health = 0;
        }
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getBlocking() {
        return blocking;
    }

    public void setBlocking(int blocking) {
        this.blocking = blocking;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
