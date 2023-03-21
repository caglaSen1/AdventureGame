public class SafeHouse extends NormalLocation{
    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    public boolean onLocation(){
        System.out.println("******************************************************************************");
        System.out.println("");
        System.out.println("You're in the Safe House.");
        System.out.println("Your health is fully restored!");
        this.getPlayer().getCharacter().setHealth(this.getPlayer().getCharacter().getBeginningHealth());

        //IS PLAYER COLLECT ALL ITEMS AND WON?
        if(this.getPlayer().getCharacter().getInventory().getAwards().size() == 3){
            System.out.println("CONGRATULATIONS you collect all awards in every locations.");
        }

        return true;
    }
}
