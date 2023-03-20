public abstract class Characters {
    private int id;
    private String charName;
    private int damage;
    private int health;
    private int money;
    private Inventory inventory;

    public Characters(int id, String charName, int damage, int health, int money) {
        setId(id);
        setCharName(charName);
        setDamage(damage);
        setHealth(health);
        setMoney(money);

        //WHEN CHARACTER PICKED, GET AN INVENTORY
        setInventory(new Inventory());
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
        return damage + inventory.getWeapon().getDamage();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
