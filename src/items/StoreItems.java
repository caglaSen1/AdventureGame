package items;

public abstract class StoreItems {
    private int id = 0;
    private String name;
    private int price;

    public StoreItems(String name, int id, int price) {
        setName(name);
        setId(id);
        setPrice(price);

    }

    public abstract void printItemsInfo();

    public static StoreItems getItemByID(int id, StoreItems[] storeItem){
        for(StoreItems item : storeItem){
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }

    public static StoreItems getItemByName(String name, StoreItems[] storeItem) {
        for(StoreItems item : storeItem){
            if(item.getName().equals(name)){
                return item;
            }
        }return null;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}


