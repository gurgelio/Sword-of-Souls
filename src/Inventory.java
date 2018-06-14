import java.util.ArrayList;

public class Inventory {

    private String[] equiped;
    private ArrayList<String> inventory = new ArrayList<>();
    private int gold;

    public Inventory(String[] equipment){
        this.equiped = equipment;
        this.gold = 0;
    }

    void sellItem(int price, String item){
        this.inventory.remove(item);
        this.gold += price;
    }

    void buyItem(int price, String item){
        if (this.gold >= price){
            this.inventory.add(item);
            this.gold -= price;
        }
    }

    void addItem(String item){
        this.buyItem(0, item);
    }

    String[] getEquiped(){
        return this.equiped;
    }
}
