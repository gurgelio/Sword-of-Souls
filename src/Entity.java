import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class Entity {
    Vector2f pos;
    Rectangle atackHitBox = new Rectangle(0,0,w/2,h/2);
    private boolean atacking = false;
    int hp, dexterity, strength, constitution, willpower;
    float speed;
    String direction;

    private Map<String, String> inventory;
    Anim animation;
    int w = 64, h = 64;

    Entity(String[]equipment, int strength, int dexterity, int constitution, int willpower) throws SlickException {

        hp = (int) (50 + Math.sqrt(constitution));
        speed = (float) (0.05 + Math.sqrt(dexterity)*0.1);
        if(speed > 1.5f) speed = 1.5f;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.willpower = willpower;

        this.inventory = new HashMap<>();
        this.inventory.putAll(Items.createinventory(equipment));
        ArrayList<String> actions = new ArrayList<>();
        for(String str : new String[] {"behind", "body", "feet", "legs", "torso",  "belt", "head", "hands"}){
            if(inventory.containsKey(str)) actions.add(Items.items.get(inventory.get(str)));
        }
        animation = new Anim(actions, dexterity);

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

    float[] hitbox() {
        return new float[]{getX() + w / 4, getY() + h / 2};
    }

    void update(GameContainer gc, int delta, Play gps) {

    }

    void die() {
        animation.setState("die");

    }

    void thrust() {
        animation.setState("thrust");
        if(animation.getFrame() == 7){

            animation.setFrame(4);
        }
    }

    void cast() {
        animation.setState("cast");
    }

    void slash() {
        animation.setState("slash");
        }

    void shoot() {
        animation.setState("shoot");
        if(animation.getFrame() == 12){
                animation.setFrame(4);
            }
        }

    void render(Graphics g) {
        animation.render(pos.x, pos.y);
        if (atacking) g.draw(atackHitBox);
    }

    void atackUpdate(String direction, Rectangle atackHitBox, int range) {
        switch (direction) {
            case "right":
                atackHitBox.setBounds(pos.x + 48, pos.y + 32, range, 32);
                break;
            case "left":
                atackHitBox.setBounds(pos.x + 16 - range, pos.y + 32, range, 32);
                break;
            case "up":
                atackHitBox.setBounds(pos.x + 16, pos.y + 32 - range, 32, range);
                break;
            case "down":
                atackHitBox.setBounds(pos.x + 16, pos.y + 64, 32, range);
                break;
        }
    }

}