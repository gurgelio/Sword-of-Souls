import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Vector;

public class Item {

    private int buyCost, sellCost;
    private String name;
    private Image image;
    private Vector<Integer> inventoryPosition = new Vector<>(2);
    public Item(String name) throws SlickException {
        this.name = name;
        this.image = new Image("anim/"+name+".png").getSubImage(0,128,64,64);
    }

    void renderImage(Graphics g, float x, float y){
        g.drawImage(this.image, x + inventoryPosition.get(0)*32, y + inventoryPosition.get(1)*32);
    }

    @Override
    public String toString(){
        return this.name;
    }
}
