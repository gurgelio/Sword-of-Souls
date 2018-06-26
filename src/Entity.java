import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;

abstract class Entity {
    Vector2f pos;
    int hp, mana;
    String direction;
    float speed;
    ArrayList<Action> charAnimation = new ArrayList<>();
    private int w = 64, h = 64;
    private Inventory inventory;

    Entity(String[]equipment, int dexterity) throws SlickException {
        inventory = new Inventory(equipment);
        hp = 100;
        mana = 100;
        speed = (float) (Math.log10(dexterity + 1)/3);
        if(speed > 1.5f) speed = 1.5f;

        for (String st : inventory.getEquipped()){
            charAnimation.add(new Action(new Image("anim/"+st+".png"),64,64,120));
        }
        direction = "down";
    }

    static void render(ArrayList<Entity> entities){
        for(Entity e : entities) {
            for (Action act : e.charAnimation) act.render(e.pos.x, e.pos.y);
        }
    }

    void update(int delta, Mapa map){
    }

    void setItem(Image img, Action act){
        act.setItem(img, 120, 64, 64);
    }

    static void update(ArrayList<Entity> entities, int delta, Mapa map){
        for(Entity e : entities){
            e.update(delta, map);
        }
    }

    float getX() {
        return pos.x;
    }

    float getY() {
        return pos.y;
    }


    void setpos(float x, float y) {
        this.pos.x = 32*x;
        this.pos.y = 32*y;
    }

    float[] collisionBox() {
        return new float[]{getX() + w/2, getY() + 3*h/4, w/4};
    }

    float[] hitbox() { return new float[]{getX(), getY(), getX() + w, getY() + h}; }

    void die() {
        for (Action act : charAnimation) {
            act.setState("die");
            if (act.isStopped()) {
                act.setFrame(0);
                act.start();
            }
        }
    }

    void thrust() {
        for (Action act : charAnimation) {
            act.setState("thrust");
            if (act.isStopped()) {
                act.setFrame(4);
                act.start();
                DamageBox.createBox(this.getX(), this.getY(), 8, 10, direction, 120);
            }
        }
    }

    void cast() {
        for (Action act : charAnimation) {
            act.setState("cast");
            if (act.isStopped()) {
                //spell effect
                act.setFrame(0);
                act.start();
                act.setState("stop");
            }
        }
    }

    void slash() {
        for (Action act : charAnimation) {
            act.setState("slash");
            if (act.getFrame() == act.slash.length - 1){
                DamageBox.createBox(this.getX(), this.getY(), 8, 10, direction,120);
            }
        }
    }

    void shoot() {
        for (Action act : charAnimation) {
            act.setState("shoot");
            if (act.getFrame() == 12) {
                act.setFrame(4);
            }
        }
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

    Inventory getInventory(){
        return this.inventory;
    }

}