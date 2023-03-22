package items;

public class Weapon extends StoreItems {
    private int damage;

    public Weapon(String name, int id, int damage, int price) {
        super(name, id, price);
        setDamage(damage);
    }


    //ALL WEAPONS
    public static Weapon[] weapons(){
        Weapon[] weaponList = new Weapon[3];
        weaponList[0] = new Weapon("Gun", 1, 2, 5);
        weaponList[1] = new Weapon("Sword", 2, 3, 35);
        weaponList[2] = new Weapon("Rifle", 3, 7, 45);
        return weaponList;
    }



    @Override
    public void printItemsInfo() {

        System.out.println("************ Weapons ************");
        for(Weapon weapon : weapons()){
            System.out.println(weapon.getId() + " - " + weapon.getName() + " [Price: " + weapon.getPrice()
                    + " | " + "Damage: " + weapon.getDamage() + "]");
        }
        System.out.println("4 - Exit");
        System.out.println("*********************************");

    }



    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
