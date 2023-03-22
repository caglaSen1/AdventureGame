package locations;
import player.*;
import java.util.Scanner;

public abstract class Location {
    private Player player;
    private String name;
    private int id;

    //There are 3 location to fight
    private final int howManyBattleLoc = 3;
    public static Scanner scan = new Scanner(System.in);

    public Location(Player player, String name, int id) {
        setPlayer(player);
        setName(name);
        setId(id);
    }

    abstract public boolean onLocation();

    public int getHowManyBattleLoc() {
        return howManyBattleLoc;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
