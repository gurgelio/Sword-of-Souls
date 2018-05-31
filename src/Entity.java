import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class Entity {
    Vector2f pos;
    int hp = 100;
    private Map<String, String> inventory;
    Anim animation;
    int w=64, h=64;

    Entity(String[]equipment) throws SlickException {
        this.inventory = new HashMap<>();
        this.inventory.putAll(Items.createinventory(equipment));
        ArrayList<String> actions = new ArrayList<>();
        for(String str : new String[] {"body", "head", "torso", "hands", "belt", "legs", "feet", "weapon", "behind"}){
            if(inventory.containsKey(str)) actions.add(Items.items.get(inventory.get(str)));
        }
        animation = new Anim(actions);

    }
    float getX() {
        return pos.x;
    }
    float getY() {
        return pos.y;
    }
    Vector2f getpos() {
        return pos;
    }

    void setpos(int x, int y) {
        this.pos.x = x;
        this.pos.y = y;
    }

    float[] hitbox(){
        return new float[]{getX() + w/4, getY() + h/2};
    }

    void update(GameContainer gc, int delta, Play gps){

    }

    void die(){
        animation.setState("die");
    }

    void thrust(){
        animation.setState("thrust");

    }

    void cast(){
        animation.setState("cast");
    }

    void slash(){
        animation.setState("slash");
    }

    void shoot(){
        animation.setState("shoot");
    }



}