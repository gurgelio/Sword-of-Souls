import org.newdawn.slick.SlickException;

import java.util.ArrayList;

class Inventory {

    private String[] equipped;
    private ArrayList<Item> inventory = new ArrayList<>();
    private int gold;

    Inventory(String[] equipment) throws SlickException {
        this.equipped = equipment;
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

    String[] getEquipped(){
        return this.equipped;
    }
}
