public abstract class StoreItems {
    private String name;
    private int id = 0;
    private int price;

    public StoreItems(String name, int id, int price) {
        setName(name);
        setId(id);
        setPrice(price);
    }

    public abstract void printItemsInfo();

    public static StoreItems[] storeItemsList(StoreItems[] storeItems){

        return storeItems;
    }

    public static StoreItems getItemByID(int id, StoreItems[] storeItem){
        for(StoreItems item : storeItem){
            if(item.getId() == id){
                return item;
            }
        }
        return null;
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


