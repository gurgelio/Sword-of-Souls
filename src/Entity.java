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
    int w = 64, h = 64;
    private Inventory inventory;

    Entity(String[]equipment, int dexterity) throws SlickException {

        inventory = new Inventory(equipment);
        hp = 100;
        mana = 100;
        speed = (float) (Math.log10(dexterity + 1)/3);
        if(speed > 1.5f) speed = 1.5f;

        for (String st : inventory.getEquiped()){
            charAnimation.add(new Action(new Image(st),64,64,120));
        }

    }

    private void render() {
        for (Action act : charAnimation) act.render(pos.x, pos.y);
    }

    static void render(ArrayList<Entity> entities){
        for(Entity e : entities) {
            e.render();
        }
    }

    void update(int delta, Mapa map){
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


    void setpos(int x, int y) {
        this.pos.x = x;
        this.pos.y = y;
    }

    float[] hitbox() {
        return new float[]{getX() + w/2, getY() + 3*h/4, w/4};
    }

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