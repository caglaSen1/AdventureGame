public abstract class Characters {
    private int id;
    private String charName;
    private int damage;
    private int health;
    private int money;

    public Characters(int id, String charName, int damage, int health, int money) {
        setId(id);
        setCharName(charName);
        setDamage(damage);
        setHealth(health);
        setMoney(money);
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
}
