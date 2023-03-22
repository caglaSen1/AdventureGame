package locations;
import player.*;

public class SafeHouse extends NormalLocation {
    public SafeHouse(Player player) {
        super(player, "Safe House", 1);
    }

    @Override
    public boolean onLocation(){
        System.out.println("******************************************************************************");
        System.out.println("");
        System.out.println("You're in the Safe House.");
        System.out.println("Your health is fully restored!");
        this.getPlayer().getCharacter().setHealth(this.getPlayer().getCharacter().getBeginningHealth());

        return true;
    }
}
