import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Vector;

public class Item {

    private int buyCost, sellCost;
    private String name;
    private Image image;
    int[] inventoryPosition = new int[]{0,0};
    int x = 0, y = 0;

    public Item(String name, int lin, int col) throws SlickException {
        this.name = name;
        this.image = new Image("anim/"+name+".png").getSubImage(0,128,64,64).getScaledCopy(32,32);
        this.inventoryPosition[0] = lin;
        this.inventoryPosition[1] = col;
    }

    void renderImage(float x, float y){
        image.draw(this.x + x + inventoryPosition[1]*38, this.y + y + inventoryPosition[0]*38);
    }

    @Override
    public String toString(){
        return this.name;
    }
}
