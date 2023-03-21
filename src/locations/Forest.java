package locations;
import player.*;
import obstacle.*;

public class Forest extends BattleLocation {
    public Forest(Player player) {
        super(player, "Forest", new Vampire(), "Wood", 3, 4);
    }
}
