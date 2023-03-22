package items;

public class Armor extends StoreItems {
    private int blocking;

    public Armor(String name, int id, int price, int blocking) {
        super(name, id, price);
        setBlocking(blocking);
    }

    //ALL ARMORS
    public static Armor[] armors(){
        Armor[] armorList = new Armor[3];
        armorList[0] = new Armor("Light Armor", 1, 1, 15);
        armorList[1] = new Armor("Medium Armor", 2, 3, 25);
        armorList[2] = new Armor("Heavy Armor", 3, 5, 40);
        return armorList;
    }



    @Override
    public void printItemsInfo() {

        System.out.println("************ Armors ************");
        for(Armor armors : armors()){
            System.out.println(armors.getId() + " - " + armors.getName() + " [Price: " + armors.getPrice()
                    + " | " + "Blocking: " + armors.getBlocking() + "]");
        }
        System.out.println("4 - Exit");
        System.out.println("*********************************");

    }




    public int getBlocking() {
        return blocking;
    }

    public void setBlocking(int blocking) {
        this.blocking = blocking;
    }
}
