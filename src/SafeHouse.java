public class SafeHouse extends NormalLocation{
    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    public boolean onLocation(){
        System.out.println("******************************************************************************");
        System.out.println("You're in the Safe House.");
        System.out.println("Your health is fully restored!");
        System.out.println("******************************************************************************");
        return true;
    }
}
