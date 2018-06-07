import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class Entity {
    Vector2f pos;
    private boolean atacking = false;
    int hp;
    String direction;
    private int dexterity, strength, constitution, willpower;
    float speed;
    private Map<String, String> inventory;
    Anim animation;
    int w = 64, h = 64;

    Entity(String[]equipment, int strength, int dexterity, int constitution, int willpower) throws SlickException {

        hp = (int) (50 + Math.sqrt(constitution));
        speed = (float) (Math.log10(dexterity + 1)/3);
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
        return new float[]{getX() + w/2, getY() + 3*h/4, w/4};
    }

    void update(GameContainer gc, int delta, Play gps) {

    }

    void die() {
        animation.setState("die");

    }

    void thrust() {
        animation.setState("thrust");
        if(animation.isStopped()){
            animation.setFrame(4);
            animation.start();
        }
    }

    void cast() {
        animation.setState("cast");
        if(animation.isStopped()){
            //spell effect
            animation.setFrame(0);
            animation.start();
            animation.setState("stop");
        }
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

    void render() {
        animation.render(pos.x, pos.y);
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