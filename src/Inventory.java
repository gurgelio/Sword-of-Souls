import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Arrays;

class Inventory {

    private ArrayList<String> equipped = new ArrayList<>();
    private ArrayList<Item> inventory = new ArrayList<>();
    private int gold, col = 0, lin = 0;
    private boolean invState = false;
    private Image inventoryImg = new Image("img/inventoryHud.png");
    private Image equip = new Image("img/equipInventory2.png");
    private Image title = new Image("img/InventoryBaseTitle.png");
    private float x = 138, y = 0;
    private float rx, ry;

    Inventory(String[]  equipment) throws SlickException {
        this.equipped.addAll(Arrays.asList(equipment));
        this.gold = 0;
        for (String it : equipped){
            Item each = new Item(it, col, lin);
            inventory.add(each);
            if (col < 4) col++;
            else{
                lin++;
                col = 0;
            }
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

    ArrayList<Item> getInventory(){
        return this.inventory;
    }

    boolean overArea(){
        return (In.mouseIsOver(new int[] {(int) (Game.width - this.x - title.getWidth()), (int) (Game.height - this.y - inventoryImg.getHeight()), title.getWidth(), inventoryImg.getHeight()}) && invState);
    }

    void render(float a, float b){

        if (In.keyPressed("i")) invState = !invState;

        rx = a - x + Game.width - inventoryImg.getWidth();
        ry = b - y + Game.height - inventoryImg.getHeight() + 24;

        if (invState) {
            inventoryImg.draw( a - x + Game.width - inventoryImg.getWidth(), b - y + Game.height - inventoryImg.getHeight());
            equip.draw(a - x + Game.width - inventoryImg.getWidth() - equip.getWidth(), b - y + Game.height - inventoryImg.getHeight() + 24);
            title.draw(a - x + Game.width - inventoryImg.getWidth() - equip.getWidth(), b - y + Game.height - inventoryImg.getHeight());
            if (In.mouseIsOver(new int[]{(int) -x + Game.width - title.getWidth(), (int) -y + Game.height - inventoryImg.getHeight(), 188, 24})) {
                if (In.buttonHeld("lmb")) {
                    x = Game.width - In.getMouse()[0] - title.getWidth() / 2;
                    y = Game.height - In.getMouse()[1] - inventoryImg.getHeight() + 12;
                }
            }

            for (Item it : inventory){
                it.renderImage(a - x + Game.width - inventoryImg.getWidth(), b - y + Game.height - inventoryImg.getHeight() + 24);
                if (In.mouseIsOver(new int[] {(int) rx + it.getX(), (int) ry + it.getY(), 32, 32})){
                    if (In.buttonHeld("lmb")){
                        it.setX(In.getMouse()[0]/title.getWidth());
                        it.setY(In.getMouse()[1]/(inventoryImg.getHeight() + 24));
                    }
                }
            }
        }
    }
}
