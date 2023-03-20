import java.util.Scanner;

public abstract class Location {
    private Player player;
    private String name;
    public static Scanner scan = new Scanner(System.in);
    //public Weapon getPlayersWeapon = this.getPlayer().getCharacter().getInventory().getWeapon();

    public Location(Player player, String name) {
        setPlayer(player);
        setName(name);
    }

    abstract public boolean onLocation();


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
