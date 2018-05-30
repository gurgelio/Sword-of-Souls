import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class Entity {
    Vector2f pos;
    int hp = 100, atack = 15, armor = 40;
    private Map<String, String> inventory;
    Anim animation;
    Rectangle rectangle;
    int w, h;

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

    public void setpos(int x, int y) {
        this.pos.x = x;
        this.pos.y = y;
    }

    void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    Rectangle getRectangle() {
        return rectangle;
    }

    float[] hitbox(){
        return new float[]{(float) (getX() + w/4), (float) (getY() + h/2)};
    }

}