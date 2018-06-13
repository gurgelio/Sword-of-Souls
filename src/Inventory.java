import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class Inventory {

    private String[] equiped;
    private ArrayList<String> inventory;
    private Action[] character = {null,null,null,null,null,null,null,null,null,null};

    public Inventory(String[] equipment) throws SlickException {
        this.equiped = equipment;
        for (Action act : character){
            for (String st : equiped){
                act = new Action(new Image(Items.items.get(st)), 64, 64, 120, 3);
            }
        }
    }

    void render(float x, float y, Animation[] action){
        for (Action act : character){
            act.render(x, y, action);
        }
    }
}
