import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

class Inventory {

    private ArrayList<String> equipped = new ArrayList<>();
    private ArrayList<Item> inventory = new ArrayList<>();
    private int gold;

    Inventory(String[]  equipment) throws SlickException {
        for (String s : equipment) this.equipped.add(s);
        this.gold = 0;
        for (String it : equipped){
            inventory.add(new Item(it));
        }
    }

    void sellItem(int price, String item){
        this.inventory.remove(item);
        this.gold += price;
    }

    void buyItem(int price, Item item){
        if (this.gold >= price){
            this.inventory.add(item);
            this.gold -= price;
        }
    }

    void addItem(Item item){
        this.buyItem(0, item);
    }

    void setItem(int index, String item, Entity e) throws SlickException {
        this.equipped.set(index, item);
        e.setItem(new Image("anim/"+item+".png"), e.charAnimation.get(index));
    }

    ArrayList<String> getEquipped(){
        return this.equipped;
    }
}
