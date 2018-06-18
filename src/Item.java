import java.io.File;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Item {

    private int buyCost, sellCost;
    private String name;
    private Image image;
    private int[] inventoryPosition = new int[]{0,0};
    private int x, y;

    public Item(String name, int lin, int col) throws SlickException {
        this.name = name;
        if (new File("anim/Icons/"+name+".png").isFile()) this.image = new Image("anim/Icons/"+name+".png");
        else this.image = new Image("anim/"+name+".png").getSubImage(0,128,64,64).getScaledCopy(32,32);
        this.inventoryPosition[0] = lin;
        this.inventoryPosition[1] = col;
        this.x = inventoryPosition[0]*38;
        this.y = inventoryPosition[1]*38;
    }

    void renderImage(float x, float y){
        image.draw(this.x + x, this.y + y);
    }

    @Override
    public String toString(){
        return this.name;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return y;
    }

    void setInventoryPosition(int[] inventoryPosition) {
        this.inventoryPosition = inventoryPosition;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }
}
