package player;
import items.*;
import java.util.ArrayList;

public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private ArrayList awards = new ArrayList();
    private int collectedItemNum = 0;

    public Inventory() {
        this.weapon = new Weapon("Punch", 0, 0, 0);
        this.armor = new Armor("No Armor", 0, 0, 0);
    }

    //If award hasn't collected yet return false else return true
    public boolean isAwardCollected(String awardOfLoc){
        for(Object award : awards){
            if(award.equals(awardOfLoc)){
                return true;
            }

        }return false;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public ArrayList getAwards() {
        return awards;
    }

    public void setAwards(String award) {
        awards.add(collectedItemNum, award);
        collectedItemNum++;

    }


}
